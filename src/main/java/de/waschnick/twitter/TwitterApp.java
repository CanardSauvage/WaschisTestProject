package de.waschnick.twitter;

import java.util.List;

public class TwitterApp {

    public static void main(String[] args) throws Exception {

        final TwitterCredentials twitterCredentials = new TwitterCredentials();

        final TwitterClient twitterClient = new TwitterClient(twitterCredentials);

        List<Tweet> result = twitterClient.getTopStoriesOfUser("as_ideas", 10);

        for (Tweet tweet : result) {
            // FIXME Remove System.out
            System.out.println("\n" + tweet);
        }
    }
}
