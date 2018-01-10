package de.waschnick.twitter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Twitter {

    public void testUserTimelineWithAuthSample() throws Exception {
        //This will read the timeline of your account.
        String method = "GET";
        String url = "https://api.twitter.com/1.1/statuses/user_timeline.json";

        String oAuthConsumerKey = "Your value here.";
        String oAuthConsumerSecret = "Your value here."; //<--- DO NOT SHARE THIS VALUE

        String oAuthAccessToken = "Your value here.";
        String oAuthAccessTokenSecret = "Your value here."; //<--- DO NOT SHARE THIS VALUE

        String oAuthNonce = String.valueOf(System.currentTimeMillis());
        String oAuthSignatureMethod = "HMAC-SHA1";
        String oAuthTimestamp = time();
        String oAuthVersion = "1.0";

        String signatureBaseString1 = method;
        String signatureBaseString2 = url;
        String signatureBaseString3Templ = "oauth_consumer_key=%s&oauth_nonce=%s&oauth_signature_method=%s&oauth_timestamp=%s&oauth_token=%s&oauth_version=%s";
        String signatureBaseString3 = String.format(signatureBaseString3Templ,
                oAuthConsumerKey,
                oAuthNonce,
                oAuthSignatureMethod,
                oAuthTimestamp,
                oAuthAccessToken,
                oAuthVersion);

        String signatureBaseStringTemplate = "%s&%s&%s";
        String signatureBaseString =  String.format(signatureBaseStringTemplate,
                URLEncoder.encode(signatureBaseString1, "UTF-8"),
                URLEncoder.encode(signatureBaseString2, "UTF-8"),
                URLEncoder.encode(signatureBaseString3, "UTF-8"));

        System.out.println("signatureBaseString: "+signatureBaseString);

        String compositeKey = URLEncoder.encode(oAuthConsumerSecret, "UTF-8") + "&" + URLEncoder.encode(oAuthAccessTokenSecret, "UTF-8");

        String oAuthSignature =  computeSignature(signatureBaseString, compositeKey);
        System.out.println("oAuthSignature       : "+oAuthSignature);

        String oAuthSignatureEncoded = URLEncoder.encode(oAuthSignature, "UTF-8");
        System.out.println("oAuthSignatureEncoded: "+oAuthSignatureEncoded);

        String authorizationHeaderValueTempl = "OAuth oauth_consumer_key=\"%s\", oauth_nonce=\"%s\", oauth_signature=\"%s\", oauth_signature_method=\"%s\", oauth_timestamp=\"%s\", oauth_token=\"%s\", oauth_version=\"%s\"";

        String authorizationHeaderValue = String.format(authorizationHeaderValueTempl,
                oAuthConsumerKey,
                oAuthNonce,
                oAuthSignatureEncoded,
                oAuthSignatureMethod,
                oAuthTimestamp,
                oAuthAccessToken,
                oAuthVersion);
        System.out.println("authorizationHeaderValue: " + authorizationHeaderValue);


        System.out.println("url: " + url);
        System.out.println("authorizationHeaderValue:" + authorizationHeaderValue);

        //GetMethod getMethod = new GetMethod(url);
        //getMethod.addRequestHeader("Authorization", authorizationHeaderValue);
        //HttpClient cli = new HttpClient();
        //int status = cli.executeMethod(getMethod);

        RestClient restClient = new RestClient().withHeader("Authorization", authorizationHeaderValue);
RestClientResponse response = restClient.httpGET();
int status = response.status();
        System.out.println("Status:"+status);

//        long responseContentLength = getMethod.getResponseContentLength();
  //      System.out.println("responseContentLength:"+responseContentLength);

        String responseBody = response.body();
        System.out.println("response: "+responseBody);
    }

    public static void main(String[] args) throws Exception {
        new Twitter().testUserTimelineWithParams();
    }

    public void testUserTimelineWithParams() throws Exception {
        //This will read the timeline of the 'twitterapi' account.

        String method = "GET";
        String url = "https://api.twitter.com/1.1/statuses/user_timeline.json";
        List<NameValuePair> urlParams = new ArrayList<NameValuePair>();
        urlParams.add( new NameValuePair("screen_name","twitterapi") );
        urlParams.add( new NameValuePair("count", "10") );

        String oAuthConsumerKey = "TURejln26lkhYNNdit1kxzVdh";
        String oAuthConsumerSecret = "jq0Q24Ar7VXo6I9JwS9OWjdhAr1cOOZWpEk74znApem72bZDxw"; //<--- DO NOT SHARE THIS VALUE

        String oAuthAccessToken = "368790789-9deOTDz3CrGB6hISOZFIa1JM2cXoLz7hV5c2Ch49";
        String oAuthAccessTokenSecret = "q85sieFjCIDGDLvkbhZjpqxcAKSDRQyBYFSFrtMNIGH77"; //<--DO NOT SHARE THIS VALUE

        String oAuthNonce = String.valueOf(System.currentTimeMillis());
        String oAuthSignatureMethod = "HMAC-SHA1";
        String oAuthTimestamp = time();
        String oAuthVersion = "1.0";

        String signatureBaseString1 = method;
        String signatureBaseString2 = url;

        List<NameValuePair> allParams = new ArrayList<NameValuePair>();
        allParams.add(new NameValuePair("oauth_consumer_key", oAuthConsumerKey));
        allParams.add(new NameValuePair("oauth_nonce", oAuthNonce));
        allParams.add(new NameValuePair("oauth_signature_method", oAuthSignatureMethod));
        allParams.add(new NameValuePair("oauth_timestamp", oAuthTimestamp));
        allParams.add(new NameValuePair("oauth_token", oAuthAccessToken));
        allParams.add(new NameValuePair("oauth_version", oAuthVersion));
        allParams.addAll(urlParams);

        Collections.sort(allParams, new NvpComparator());

        StringBuffer signatureBaseString3 = new StringBuffer();
        for(int i=0;i<allParams.size();i++)
        {
            NameValuePair nvp = allParams.get(i);
            if (i>0) {
                signatureBaseString3.append("&");
            }
            signatureBaseString3.append(nvp.getName() + "=" + nvp.getValue());
        }

        String signatureBaseStringTemplate = "%s&%s&%s";
        String signatureBaseString =  String.format(signatureBaseStringTemplate,
                URLEncoder.encode(signatureBaseString1, "UTF-8"),
                URLEncoder.encode(signatureBaseString2, "UTF-8"),
                URLEncoder.encode(signatureBaseString3.toString(), "UTF-8"));

        System.out.println("signatureBaseString: "+signatureBaseString);

        String compositeKey = URLEncoder.encode(oAuthConsumerSecret, "UTF-8") + "&" + URLEncoder.encode(oAuthAccessTokenSecret, "UTF-8");

        String oAuthSignature =  computeSignature(signatureBaseString, compositeKey);
        System.out.println("oAuthSignature       : "+oAuthSignature);

        String oAuthSignatureEncoded = URLEncoder.encode(oAuthSignature, "UTF-8");
        System.out.println("oAuthSignatureEncoded: "+oAuthSignatureEncoded);

        String authorizationHeaderValueTempl = "OAuth oauth_consumer_key=\"%s\", oauth_nonce=\"%s\", oauth_signature=\"%s\", oauth_signature_method=\"%s\", oauth_timestamp=\"%s\", oauth_token=\"%s\", oauth_version=\"%s\"";

        String authorizationHeaderValue = String.format(authorizationHeaderValueTempl,
                oAuthConsumerKey,
                oAuthNonce,
                oAuthSignatureEncoded,
                oAuthSignatureMethod,
                oAuthTimestamp,
                oAuthAccessToken,
                oAuthVersion);
        System.out.println("authorizationHeaderValue: "+authorizationHeaderValue);

        StringBuffer urlWithParams = new StringBuffer(url);
        for(int i=0;i<urlParams.size();i++) {
            if(i==0)
            {
                urlWithParams.append("?");
            }
            else
            {
                urlWithParams.append("&");
            }
            NameValuePair urlParam = urlParams.get(i);
            urlWithParams.append(urlParam.getName() + "=" + urlParam.getValue());
        }

        System.out.println("urlWithParams: "+urlWithParams.toString());
        System.out.println("authorizationHeaderValue:"+authorizationHeaderValue);

        GetMethod getMethod = new GetMethod(urlWithParams.toString());
        getMethod.addRequestHeader("Authorization", authorizationHeaderValue);

        HttpClient cli = new HttpClient();
        int status = cli.executeMethod(getMethod);
        System.out.println("Status:"+status);

        long responseContentLength = getMethod.getResponseContentLength();
        System.out.println("responseContentLength:"+responseContentLength);

        String response = getMethod.getResponseBodyAsString();
        System.out.println("response: "+response);
    }


    private static String computeSignature(String baseString, String keyString) throws GeneralSecurityException, UnsupportedEncodingException, Exception
    {
        SecretKey secretKey = null;

        byte[] keyBytes = keyString.getBytes();
        secretKey = new SecretKeySpec(keyBytes, "HmacSHA1");

        Mac mac = Mac.getInstance("HmacSHA1");

        mac.init(secretKey);

        byte[] text = baseString.getBytes();

        return new String(Base64.encodeBase64(mac.doFinal(text))).trim();
    }

    private String time() {
        long millis = System.currentTimeMillis();
        long secs = millis / 1000;
        return String.valueOf( secs );
    }

}
