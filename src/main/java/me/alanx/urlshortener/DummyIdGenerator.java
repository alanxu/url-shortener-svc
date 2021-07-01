package me.alanx.urlshortener;

import org.springframework.stereotype.Service;

@Service
public class DummyIdGenerator implements IdGenerator{
    private int count = 0;
    @Override
    public int nextId() {
        count ++;
        return count;
    }
}
