package de.waschnick.twitter;

public class RestClientResponse {

    private int status;

    private String body;

    public RestClientResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    public int status() {
        return status;
    }

    public String body() {
        return body;
    }

}
