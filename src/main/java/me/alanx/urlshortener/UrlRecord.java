package me.alanx.urlshortener;

public class UrlRecord {
    private final int id;
    private final String token;
    private final String url;

    public UrlRecord(int id, String token, String url) {
        this.id = id;
        this.token = token;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getUrl() {
        return url;
    }
}
