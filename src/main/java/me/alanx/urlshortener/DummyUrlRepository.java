package me.alanx.urlshortener;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DummyUrlRepository implements UrlRepository {
    private final Map<String, UrlRecord> store = new HashMap<>();
    @Override
    public void saveUrl(UrlRecord record) {
        store.put(record.getToken(), record);
    }

    @Override
    public UrlRecord getUrl(String token) {
        return store.get(token);
    }
}
