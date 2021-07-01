package me.alanx.urlshortener;

public interface UrlRepository {
    void saveUrl(UrlRecord record);
    UrlRecord getUrl(String token);
}
