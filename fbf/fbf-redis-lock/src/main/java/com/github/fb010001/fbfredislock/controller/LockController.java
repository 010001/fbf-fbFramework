package com.github.fb010001.fbfredislock.controller;

import com.github.fb010001.fbfredislock.annotation.CacheLock;
import com.github.fb010001.fbfredislock.annotation.CacheParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LockController {

    @CacheLock(prefix = "test")
    @GetMapping("/test")
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }

}
