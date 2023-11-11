package aed.twualger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

import aed.twualger.tests.TwualgerATests;

public class TwualgerA extends Twualger {

    private ArrayList<UserCacheA> cache;
    private int hit = 0;
    private int miss = 0;

    public static UserCacheA readUserTweetsFromFile(String path, String userHandle) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        UserCacheA result = new UserCacheA(userHandle);
        try {
            File file = new File(path + "/" + userHandle + ".csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] tweetSplit;
            while ((line = br.readLine()) != null) {
                tweetSplit = line.split(",", 3);
                Tweet tweet = new Tweet(Long.parseLong(tweetSplit[0]),
                        OffsetDateTime.parse(tweetSplit[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZZZZZ")),
                        userHandle, tweetSplit[2]);
                tweets.add(tweet);
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.tweets = tweets;
        // result.useCount++;
        return result;
    }

    public TwualgerA(String path) {
        super(path);
        this.cache = new ArrayList<UserCacheA>();
        List<String> topCelebs = Twualger.readTopCelebs(path);
        for (int i = 0; i < topCelebs.size(); i++) {
            UserCacheA userCacheA = readUserTweetsFromFile(path, topCelebs.get(i));
            userCacheA.isTop = true;
            // result.useCount++;
            this.cache.add(userCacheA);
        }

    }

    public ArrayList<UserCacheA> getCaches() {
        return this.cache;
    }

    public UserCacheA getUserCache(String userHandle) {
        UserCacheA result = null;
        for (int i = 0; i < this.cache.size(); i++) {
            if (this.cache.get(i).userName.equals(userHandle)) {
                result = this.cache.get(i);
                hit++;
                result.useCount++;
                break;
            }
        }
        if(result == null){
            result = readUserTweetsFromFile(path, userHandle);
            miss++;
            result.useCount++;
            this.cache.add(result);
        }
        return result;
    }

    public static int compareDate(Tweet a, Tweet b) {
        return b.date.compareTo(a.date);
    }

    public List<Tweet> buildTimeLine(List<String> following, OffsetDateTime from, OffsetDateTime to) {
        List<Tweet> result = new ArrayList<>();
        ArrayList<Tweet> tweets;
        UserCacheA followingCache;
        for (int i = 0; i < following.size(); i++) {
            followingCache = getUserCache(following.get(i));
            tweets = followingCache.tweets;
            for (int j = 0; j < tweets.size(); j++) {
                if (tweets.get(j).date.compareTo(from) >= 0 && tweets.get(j).date.compareTo(to) <= 0) {
                    result.add(tweets.get(j));
                }
            }
        }
        result.sort(TwualgerA::compareDate);
        return result;
    }

    public int totalSearches() {
        return hit + miss;
    }

    public float cacheHitRatio() {
        return totalSearches() == 0 ? 0 : ((float) hit) / ((float) miss + (float) hit);
    }


    public static int compare(UserCacheA a, UserCacheA b) {
        Boolean aTop = a.isTop;
        Boolean bTop = b.isTop;
        int result = bTop.compareTo(aTop);
        if (result == 0)
            result = b.useCount - a.useCount;
        return result;
     }

    // public static int compareCount(UserCacheA a, UserCacheA b) {
    //         return !a.isTop && !b.isTop ? b.useCount - a.useCount : 0;
    //  }

    public void downsizeCache() {
        int limit = (this.cache.size()) / 2;
        int n = this.cache.size();
        this.cache.sort(TwualgerA::compare);
        for (int i = n - 1; i >= 0 && limit > 0; i--) {
            if (!this.cache.get(i).isTop) {
                this.cache.remove(i);
                limit--;
            }
        }
        hit = 0;
        miss = 0;
        for (int i = 0; i < this.cache.size(); i++) {
            this.cache.get(i).useCount = 0;
        }
    }

    public static void main(String[] args) {
        // TwualgerA twualger = new TwualgerA("data");
        String path = "C:\\Users\\afons\\OneDrive\\Documentos\\aed\\p4\\data";
        ArrayList<String> following = new ArrayList<String>(Arrays.asList("zendaya",
                "RobertDowneyJr", "Cristiano"));

        // List<Tweet> tweets = twualger.buildTimeLine(
        // following,
        // OffsetDateTime.of(2022,4,1,0,0,0,0, ZoneOffset.UTC),
        // OffsetDateTime.of(2022,4,29,23,59,0,0,ZoneOffset.UTC));

        // printTweets(tweets);
        // readUserTweetsFromFile("C:\\Users\\afons\\OneDrive\\Documentos\\aed\\p4\\data",
        // "elonmusk");
        TwualgerA a = new TwualgerA(path);
        // for (int i = 0; i < a.cache.size(); i++) {
        // for (int j = 0; j < a.cache.get(i).tweets.size(); j++) {
        // System.out.println(a.cache.get(i).tweets.get(j).toString());
        // }
        // }
        // List<Tweet> tweets = a.buildTimeLine(following, OffsetDateTime.of(2022, 4, 1,
        //         0, 0, 0, 0, ZoneOffset.UTC),
        //         OffsetDateTime.of(2022, 4, 29, 23, 59, 0, 0, ZoneOffset.UTC));
        // for (int i = 0; i < tweets.size(); i++) {
        //     System.out.println(tweets.get(i).toString());
        // }
        // UserCacheA b = a.getUserCache("elonmusk");
        // System.out.println(b.tweets.get(5).toString());
        // List<String> topCelebs =
        // Twualger.readTopCelebs("C:\\Users\\Afonso\\Documents\\aed\\twualger\\data");
        // for (int i = 0; i < topCelebs.size(); i++) {
        // System.out.println(topCelebs.get(i));
        // }

        // for (int i = 0; i < a.cache.size(); i++) {
        // System.out.println(a.cache.get(i).userName);
        // }

        for(int i = 0; i < 55; i++){
            a.getUserCache(Twualger.readAllCelebs(path).get(i));
        }
        a.cache.sort(TwualgerA::compare);
        for(int i = 0; i < a.cache.size(); i++){
            System.out.println(a.cache.get(i).userName);
        }
    //     System.out.println(a.cache.size());
    //     for(int i = 0; i < a.cache.size(); i++){
    //     System.out.println(a.cache.get(i).userName);
    //     }
    //     for(int i = 0; i < 30; i++){
    //         a.getUserCache("amrkhaled");
    //     }
    //     a.downsizeCache();
    //     System.out.println("-----------------------------------------------------------");
    //     for(int i = 0; i < a.cache.size(); i++){
    //         System.out.println(a.cache.get(i).userName);
    //         }
    //     System.out.println(a.cache.size());
    // }

    }
}