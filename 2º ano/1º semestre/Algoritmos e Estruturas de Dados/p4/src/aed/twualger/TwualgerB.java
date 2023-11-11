package aed.twualger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import aed.tables.Treap;

public class TwualgerB extends Twualger {

    HashMap<String, UserCacheB> cache;
    private int hit = 0;
    private int miss = 0;


    public TwualgerB(String path)
    {
        super(path);
        this.cache = new  HashMap<String, UserCacheB>();
        List<String> topCelebs = Twualger.readTopCelebs(path);
        for (int i = 0; i < topCelebs.size(); i++) {
            UserCacheB userCacheB = readUserTweetsFromFile(path, topCelebs.get(i));
            userCacheB.isTop = true;
            this.cache.put(topCelebs.get(i), userCacheB);
        }
    }

	public List<UserCacheB> getCaches()
    {
        return new ArrayList<UserCacheB>(this.cache.values());
    }

    public static UserCacheB readUserTweetsFromFile(String path, String userHandle)
    {
        @SuppressWarnings("unchecked")
        Treap<OffsetDateTime, Tweet> tweets = new Treap();
        UserCacheB result = new UserCacheB(userHandle);
        try {
            File file = new File(path + "/" + userHandle + ".csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] tweetSplit;
            while ((line = br.readLine()) != null) {
                tweetSplit = line.split(",", 3);
                Tweet tweet = new Tweet(Long.parseLong(tweetSplit[0]), OffsetDateTime.parse(tweetSplit[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZZZZZ")),
                    userHandle, tweetSplit[2]);
                tweets.put(tweet.date, tweet);
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.tweets = tweets;
        return result;
    }

    public UserCacheB getUserCache(String userHandle)
    {
        UserCacheB result = this.cache.get(userHandle);
        if (result != null) {
            hit++;
            result.useCount++;
        }
        else{
            result = readUserTweetsFromFile(path, userHandle);
            miss++;
            result.useCount++;
            this.cache.put(userHandle, result);
        }
        return result;
    }

    public List<Tweet> buildTimeLine(List<String> following, OffsetDateTime from, OffsetDateTime to) {
        List<Tweet> result = new ArrayList<>();
        UserCacheB followingCache;
        for (int i = 0; i < following.size(); i++) {
            followingCache = getUserCache(following.get(i));
            Iterable<Tweet> tweetIterable= followingCache.tweets.values();
            for (Tweet tweet : tweetIterable) {
                if (tweet.date.compareTo(from) >= 0 && tweet.date.compareTo(to) <= 0) {
                    result.add(tweet);
                }
            }
        }
        result.sort((Tweet a, Tweet b) -> b.date.compareTo(a.date));
        return result;
    }

    public int totalSearches()
    {
        return hit + miss;
    }

    public float cacheHitRatio()
    {
        return totalSearches() == 0 ? 0 : ((float) hit) / ((float) miss + (float) hit);
    }

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
        listCache.sort(TwualgerB::compare);
        for (int i = n-1; i >= 0 && limit > 0; i--) {
            if (!listCache.get(i).isTop) {
                this.cache.remove(listCache.get(i).userName);
                listCache.remove(i);
                limit--;
            }
            else{
                UserCacheB userCache = listCache.get(i);
                userCache.useCount = 0;
                this.cache.replace(userCache.userName, userCache);
            }
        }
        hit = 0;
        miss = 0;
    }

    public static void main(String[] args)
    {
        String path = "C:\\Users\\afons\\OneDrive\\Documentos\\aed\\p4\\data";
        aed.twualger.TwualgerB twualger = new aed.twualger.TwualgerB(path);

        ArrayList<String> following = new ArrayList<String>(Arrays.asList("elonmusk","robertdowneyjr","cristiano"));

        List<Tweet> tweets = twualger.buildTimeLine(
                following,
                OffsetDateTime.of(2022,4,1,0,0,0,0, ZoneOffset.UTC),
                OffsetDateTime.of(2022,4,29,23,59,0,0,ZoneOffset.UTC));

        printTweets(tweets);

    }

}
