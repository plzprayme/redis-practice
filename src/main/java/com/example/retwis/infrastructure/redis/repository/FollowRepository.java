package com.example.retwis.infrastructure.redis.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

@Repository
public class FollowRepository {

    private final ZSetOperations<String, String> followSortedSet;
    private final ZSetOperations<String, String> followerSortedSet;

    public FollowRepository(RedisTemplate<String, String> followRedisTemplate,
                            RedisTemplate<String, String> followerRedisTemplate) {
        this.followSortedSet = followRedisTemplate.opsForZSet();
        this.followerSortedSet = followerRedisTemplate.opsForZSet();
    }
    @Cacheable(cacheNames = "follow")
    public void follow(String followId, String followerId, Long followedTime) {
        followSortedSet.add(followId, followerId, followedTime);
    }

    @Cacheable(cacheNames = "follower")
    public void follower(String followerId, String followId, Long followedTime) {
        followerSortedSet.add(followerId, followId, followedTime);
    }

}
