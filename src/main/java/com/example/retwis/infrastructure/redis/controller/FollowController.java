package com.example.retwis.infrastructure.redis.controller;

import com.example.retwis.application.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/v1")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow/{id}")
    public String follow(
            @PathVariable("id") Integer followId,
            @RequestHeader(value="X-user") Integer followerId
    ) {
        followService.follow(followerId, followerId);
    }

}
