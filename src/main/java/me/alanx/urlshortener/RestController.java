package me.alanx.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin("*")
@org.springframework.context.annotation.Configuration
public class RestController {
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private UrlRepository urlRepository;
    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @PostMapping("/shorten")
    @ResponseBody
    public Map<String, String> shorten(@RequestBody Map<String, String> params) {
        String url = params.getOrDefault("url", "None");
        System.out.println(url);
        Map<String, String> ret = new HashMap();
        int id = this.idGenerator.nextId();
        String base62 = Base62.fromBase10(id);
        ret.put("url", "http://localhost:8080/" + base62);
        this.urlRepository.saveUrl(new UrlRecord(id, base62, url));
        return ret;
    }

    @GetMapping("/retrieve")
    @ResponseBody
    public String retrieve(@RequestParam String shortenedUrl) {
        String[] segments = shortenedUrl.split("/");
        return this.urlRepository.getUrl(segments[segments.length - 1]).getUrl();
    }

    @GetMapping("/{shortUrl}")
    @ResponseBody
    public ResponseEntity redirect(@PathVariable String shortUrl) {
        String longUrl = this.retrieve(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(longUrl)).build();
    }
}

