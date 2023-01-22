package com.example.retwis.application.service;

import com.example.retwis.infrastructure.redis.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;

    public void follow(String followId, String followerId) {
        long cur = System.currentTimeMillis();
        followRepository.follow(followId, followerId, cur);
        followRepository.follower(followerId, followId, cur);
    }
}
