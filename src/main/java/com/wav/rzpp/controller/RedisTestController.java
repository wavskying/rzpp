package com.wav.rzpp.controller;

import com.wav.rzpp.annotation.ExcludeJwtVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hbw
 **/
@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @GetMapping("/test")
    @ExcludeJwtVerify
    public String test() {
        redisTemplate.opsForValue().set("name", "zhangsan");
        String name = (String) redisTemplate.opsForValue().get("name");
        return name;
    }
}
