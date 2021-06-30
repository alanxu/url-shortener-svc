package com.example.onehourservice;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin("*")
@org.springframework.context.annotation.Configuration
public class RestController {
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
        ret.put("url", url);
        return ret;
    }
}
