package com.example.retwis.application.controller;

import com.example.retwis.application.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow/{followId}")
    public String follow(
            @PathVariable String followId,
            @RequestHeader(value="X-user") String followerId
    ) {
        followService.follow(followId, followerId);
        return "";
    }

}
