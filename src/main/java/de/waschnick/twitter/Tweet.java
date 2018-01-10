package de.waschnick.twitter;

import lombok.Data;

@Data
public class Tweet {

    private String created_at;
    private Long id;
    private String id_str;
    private String text;
    private String source;
    private Boolean truncated;
    private String in_reply_to_status_id;
    private String in_reply_to_status_id_str;
    private String in_reply_to_user_id;
    private String in_reply_to_user_id_str;
    private String in_reply_to_screen_name;
    private Boolean is_quote_status;
    private Long retweet_count;
    private Long favorite_count;

}
