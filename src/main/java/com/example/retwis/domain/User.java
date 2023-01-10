package com.example.retwis.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User {
    private final Long id;
    private final String name;
    private final String password;
}
