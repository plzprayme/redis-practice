package com.example.retwis.infrastructure.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private int port;
    @Value("${spring.data.redis.database}")
    private int database;

    @Bean
    @Scope(value = "prototype")
    public RedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);
        config.setDatabase(database);
        return new LettuceConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<?, ?> followRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> t = new RedisTemplate<>();
        t.setConnectionFactory(connectionFactory);
        t.setEnableTransactionSupport(true);
        t.setKeySerializer(new StringRedisSerializer());
        t.setValueSerializer(new StringRedisSerializer());
        return t;
    }

    @Bean
    public RedisCacheManager followCacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(redisCacheConfig(Duration.ZERO, "follow::"))
                .withInitialCacheConfigurations(cacheConfigurations())
                .transactionAware()
                .build();
    }

    @Bean
    public RedisTemplate<?, ?> followerRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> t = new RedisTemplate<>();
        t.setConnectionFactory(connectionFactory);
        t.setEnableTransactionSupport(true);
        t.setKeySerializer(new StringRedisSerializer());
        t.setValueSerializer(new StringRedisSerializer());
        return t;
    }

    @Bean
    public RedisCacheManager followerCacheManager(RedisConnectionFactory followConnectionFactory) {
        return RedisCacheManager.builder(followConnectionFactory)
                .cacheDefaults(redisCacheConfig(Duration.ZERO, "follower::"))
                .withInitialCacheConfigurations(cacheConfigurations())
                .transactionAware()
                .build();
    }

    private RedisCacheConfiguration redisCacheConfig(Duration ttl, String prefix) {
        return defaultCacheConfig()
                .entryTtl(ttl)
                .computePrefixWith(cacheName -> prefix + "::" + cacheName)
                .disableCachingNullValues();
    }

    private Map<String, RedisCacheConfiguration> cacheConfigurations() {
        return Map.of("follow", defaultCacheConfig().entryTtl(Duration.ZERO).prefixCacheNameWith("follow::"),
                "follower", defaultCacheConfig().entryTtl(Duration.ZERO).prefixCacheNameWith("follower::"));

    }
}
