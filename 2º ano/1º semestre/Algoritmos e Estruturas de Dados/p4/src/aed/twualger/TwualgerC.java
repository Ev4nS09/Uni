package aed.twualger;

import aed.tables.Treap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.annotation.processing.SupportedOptions;

public class TwualgerC extends Twualger {

    //ideally we would use current datetime, but we only have tweet data until Jully 11th
    private static final OffsetDateTime CURRENT_DATE = OffsetDateTime.of(2022,07,11,23,59,59,0,ZoneOffset.UTC);
    private static final OffsetDateTime CURRENT_MINUS_72H = CURRENT_DATE.minusHours(72);

    HashMap<String,UserCacheB> cache;
    private int hit = 0;
    private int miss = 0;

    public TwualgerC(String path)
    {
        super(path);
        this.cache = new  HashMap<String, UserCacheB>();
        List<String> topCelebs = Twualger.readTopCelebs(path);
        for (int i = 0; i < topCelebs.size(); i++) {
            UserCacheB userCacheB = readUserTweetsFromFile(path, topCelebs.get(i), CURRENT_MINUS_72H);
            userCacheB.isTop = true;
            // result.useCount++;
            this.cache.put(topCelebs.get(i), userCacheB);
        }
    }
	
	public List<UserCacheB> getCaches()
    {
        return new ArrayList<UserCacheB>(this.cache.values());
    }


    public static UserCacheB readUserTweetsFromFile(String path, String userHandle, OffsetDateTime oldest)
    {
        @SuppressWarnings("unchecked")
        Treap<OffsetDateTime, Tweet> tweets = new Treap();
        UserCacheB result = new UserCacheB(userHandle);
        try 
        {
            File file = new File(path + "/" + userHandle + ".csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] tweetSplit;
            while ((line = br.readLine()) != null) {
                tweetSplit = line.split(",", 3);
                Tweet tweet = new Tweet(Long.parseLong(tweetSplit[0]), OffsetDateTime.parse(tweetSplit[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZZZZZ")), userHandle, tweetSplit[2]);
                if(tweet.date.compareTo(oldest) < 0){
                    result.moreTweetsInFile = true;
                    break;
                }
                tweets.put(tweet.date, tweet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.tweets = tweets;
        return result;
    }

    public static void updateUserCacheFromFile(UserCacheB userCache, String path, String userHandle, OffsetDateTime oldest)
    {
        try 
        {
            File file = new File(path + "/" + userHandle + ".csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String[] tweetSplit;
            // OffsetDateTime oldestCacheDate = userCache.tweets.min();
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                userCache.moreTweetsInFile = false;
                tweetSplit = line.split(",", 3);
                Tweet tweet = new Tweet(Long.parseLong(tweetSplit[0]), OffsetDateTime.parse(tweetSplit[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZZZZZ")), userHandle, tweetSplit[2]);
                if(tweet.date.compareTo(oldest) >= 0 && tweet.date.compareTo(tweet.date) <= 0){
                    userCache.tweets.put(tweet.date, tweet);
                }
                else if(tweet.date.compareTo(oldest) < 0){
                    userCache.moreTweetsInFile = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserCacheB getUserCache(String userHandle)
    {
        UserCacheB result = this.cache.get(userHandle);
        if (result != null) {
            hit++;
            result.useCount++;
        }
        else{
            result = readUserTweetsFromFile(path, userHandle, CURRENT_MINUS_72H);
            miss++;
            result.useCount++;
            this.cache.put(userHandle, result);
        }
        return result;
    }

    public List<Tweet> buildTimeLine(List<String> following, OffsetDateTime from, OffsetDateTime to)
    {
        List<Tweet> result = new ArrayList<>();
        UserCacheB followingCache;
        for (int i = 0; i < following.size(); i++) {
            followingCache = getUserCache(following.get(i));
            if(followingCache.moreTweetsInFile == true){
                updateUserCacheFromFile(followingCache, path, followingCache.userName, from);
                this.cache.replace(followingCache.userName, followingCache);
            }
            Iterable<Tweet> tweetIterable = followingCache.tweets.values();
            for (Tweet tweet : tweetIterable) {
                if (tweet.date.compareTo(from) >= 0 && tweet.date.compareTo(to) <= 0) {
                    result.add(tweet);
                }
            }
        }
        result.sort((Tweet a, Tweet b) -> b.date.compareTo(a.date));
        return result;
    }

    public int totalSearches() {
        return hit + miss;
    }

    public float cacheHitRatio() {
        return totalSearches() == 0 ? 0 : ((float) hit) / ((float) miss + (float) hit);
    }

//    public void onlyLast72hours(List<UserCacheB> listCache){
//         @SuppressWarnings("unchecked")
//         Treap<OffsetDateTime, Tweet> tweets = new Treap();
//         for(int i = 0; i < listCache.size(); i++){
//             Iterable<Tweet> tweetIterable = listCache.get(i).tweets.values();
//             for (Tweet tweet : tweetIterable) {
//                 if(tweet.date.compareTo(CURRENT_MINUS_72H) >= 0){
//                     tweets.put(tweet.date, tweet);
//                     listCache.get(i).moreTweetsInFile = true;
//                 }
//             }
//             listCache.get(i).tweets = tweets;
//         }
//    }

    public static int compare(UserCacheB a, UserCacheB b) {
        Boolean aTop = a.isTop;
        Boolean bTop = b.isTop;
        int result = bTop.compareTo(aTop);
        if (result == 0)
            result = b.useCount - a.useCount;
        return result;
    }

    public void downsizeCache()
    {
        List<UserCacheB> listCache = getCaches();
        int limit = (listCache.size()) / 2;
        int n = listCache.size();
        listCache.sort(TwualgerC::compare);
        for (int i = n-1; i >= 0 && limit > 0; i--) {
            if (!listCache.get(i).isTop) {
                this.cache.remove(listCache.get(i).userName);
                listCache.remove(i);
                limit--;
            }
        }
        for (int i = 0; i < listCache.size(); i++) {
            String userHandle = listCache.get(i).userName;
            if(listCache.get(i).tweets.min() != null && listCache.get(i).tweets.min().compareTo(CURRENT_MINUS_72H) < 0)  listCache.get(i).moreTweetsInFile = true;
            listCache.get(i).tweets.deleteLesserEqual(CURRENT_MINUS_72H);
            listCache.get(i).useCount = 0;
            this.cache.replace(userHandle, listCache.get(i));
        }
        hit = 0;
        miss = 0;
    }

    public static void main(String[] args)
    {
        String path = "C:\\Users\\afons\\OneDrive\\Documentos\\aed\\p4\\data";
        TwualgerC twualger = new TwualgerC(path);
        // Iterable<Tweet> tweetIterable = twualger.cache.get("elonmusk").tweets.values();
        ArrayList<String> following = new ArrayList<String>(Arrays.asList("elonmusk"));

        List<Tweet> tweets = twualger.buildTimeLine(
                following,
                OffsetDateTime.of(2022,5,1,0,0,0,0, ZoneOffset.UTC),
                OffsetDateTime.of(2022,6,29,23,59,0,0,ZoneOffset.UTC));

                
        System.out.println(twualger.cache.get("elonmusk").tweets.get(OffsetDateTime.of(2022,5,21,21,23,47,0, ZoneOffset.UTC)));
        twualger.downsizeCache();
        System.out.println(twualger.cache.get("elonmusk").tweets.get(OffsetDateTime.of(2022,5,21,21,23,47,0, ZoneOffset.UTC)));
        // printTweets(tweets);
        // System.out.println(twualger.cache.get("elonmusk").useCount);
        // twualger.
        // getUserCache("elonmusk");
        // System.out.println(twualger.cache.get("elonmusk").useCount);
        // System.out.println(twualger.cache.get("elonmusk").tweets.min());
        // System.out.println(twualger.cache.get("elonmusk").tweets.max());
        // Iterable<Tweet> tweetIterable = twualger.cache.get("elonmusk").tweets.values();
        // for(Tweet tweet : tweetIterable){
        //     System.out.println("[" + tweet + "]");
        // }
        // System.out.println(twualger.cache.get("elonmusk").moreTweetsInFile);
        // UserCacheB cacheElon = twualger.cache.get("elonmusk");
        // updateUserCacheFromFile(cacheElon, path, "elonmusk", OffsetDateTime.of(2022,5,3,15,39,55,0,ZoneOffset.UTC));
        // Iterable<Tweet> tweetIterable2 = cacheElon.tweets.values();
        // for(Tweet tweet : tweetIterable2){
        //     System.out.println(tweet);
        // }
        // System.out.println(cacheElon.moreTweetsInFile);
    }

}
