package com.example.retwis.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final RedisTemplate<String, String> redisTemplate;
    public void follow(Integer followerId, Integer followerId1) {

    }
    // A follow B with unixtimestamp
}
