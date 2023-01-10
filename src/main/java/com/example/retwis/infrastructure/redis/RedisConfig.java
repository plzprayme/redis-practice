package com.example.retwis.infrastructure.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.TimeoutOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private int port;
    @Value("${spring.data.redis.database}")
    private int database;



    @Bean
    public LettuceConnectionFactory getRedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);
        config.setDatabase(database);
        return new LettuceConnectionFactory(config);
//        return new LettuceConnectionFactory(getRedisStandaloneConfiguration(), getLettuceClientConfiguration());
    }

//    @Bean
//    public RedisTemplate<String, String> redisTemplate(LettuceConnectionFactory redisConnectionFacotry) {
//        RedisTemplate<String, String> t = new RedisTemplate<>();
//        t.setConnectionFactory(redisConnectionFacotry);
//        return t;
//    }
//
//    private RedisStandaloneConfiguration getRedisStandaloneConfiguration() {
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);
//        config.setDatabase(database);
//        return getRedisStandaloneConfiguration();
//    }
//
//    private LettuceClientConfiguration getLettuceClientConfiguration() {
//        return LettuceClientConfiguration.builder()
//                .clientName("Retwis")
//                .clientOptions(getClientOptions())
//                .build();
//    }
//
//    private ClientOptions getClientOptions() {
//        return ClientOptions.builder()
//                .timeoutOptions(timeoutOptions())
//                .build();
//    }
//
//    private TimeoutOptions timeoutOptions() {
//        return TimeoutOptions.builder()
//                .connectionTimeout()
//                .timeoutCommands(true)
//                .fixedTimeout(Duration.ofSeconds(5L))
//                .build();
//    }
}
