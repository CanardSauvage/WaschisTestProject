package de.waschnick.twitter;

import com.google.gson.Gson;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * inspired by http://stackoverflow.com/questions/13387025/simplest-java-example-retrieving-user-timeline-with-twitter-api-version-1-1
 * <p/>
 * Think of the signatureBaseString as a 3 part string delimited by the ampersand (METHOD&URL&PARAMS). The api parameters are NOT to be included in the 2nd part of the signatureBaseString, they are to be included (with the other 6 security parameters) in the last part of signatureBaseString (Also, those params must be in alphabetic order).
 */
public class TwitterClient {

    private TwitterCredentials twitterCredentials;

    public TwitterClient(TwitterCredentials twitterCredentials) {
        this.twitterCredentials = twitterCredentials;
    }

    public List<Tweet> getTopStoriesOfUser(String user, int count) throws Exception {
        //This will read the timeline of the 'twitterapi' account.

        String method = "GET";
        String url = "https://api.twitter.com/1.1/statuses/user_timeline.json";
        List<NameValuePair> urlParams = new ArrayList<NameValuePair>();
        urlParams.add(new NameValuePair("screen_name", user));
        urlParams.add(new NameValuePair("count", "" + count));
        String authorizationHeaderValue = twitterCredentials.calculateAuthHeader(method, url, urlParams);

        StringBuffer urlWithParams = new StringBuffer(url);
        for (int i = 0; i < urlParams.size(); i++) {
            if (i == 0) {
                urlWithParams.append("?");
            } else {
                urlWithParams.append("&");
            }
            NameValuePair urlParam = urlParams.get(i);
            urlWithParams.append(urlParam.getName() + "=" + urlParam.getValue());
        }

        System.out.println("urlWithParams: " + urlWithParams.toString());
        System.out.println("authorizationHeaderValue:" + authorizationHeaderValue);

        GetMethod getMethod = new GetMethod(urlWithParams.toString());
        getMethod.addRequestHeader("Authorization", authorizationHeaderValue);

        HttpClient cli = new HttpClient();
        int status = cli.executeMethod(getMethod);
        System.out.println("Status:" + status);

        long responseContentLength = getMethod.getResponseContentLength();
        System.out.println("responseContentLength:" + responseContentLength);

        String response = getMethod.getResponseBodyAsString();
        System.out.println("response: " + response);


        return Arrays.asList(new Gson().fromJson(response, Tweet[].class));
    }



}
