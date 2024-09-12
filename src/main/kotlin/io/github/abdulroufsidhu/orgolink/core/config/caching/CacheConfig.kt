package io.github.abdulroufsidhu.orgolink.core.config.caching

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration


@Configuration
@EnableCaching
class CacheConfig {

    @Bean()
    @Primary
    fun redisCacheManager(redisConnectionFactory: RedisConnectionFactory): CacheManager {
        val objectMapper = ObjectMapper().apply {
            registerModule(KotlinModule.Builder().build())
            registerModule(JavaTimeModule())
            registerModule(
                Hibernate6Module()
                    .enable(Hibernate6Module.Feature.FORCE_LAZY_LOADING)
                    .enable(Hibernate6Module.Feature.REPLACE_PERSISTENT_COLLECTIONS)
            )
            setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
            activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
            )
        }
        val serializer = GenericJackson2JsonRedisSerializer(objectMapper)

        val cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(1)) // Set cache TTL (time to live)
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer())
            )
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(serializer)
            )

        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(cacheConfig)
            .transactionAware()
            .build()
    }

    @Bean
    fun cacheConfiguration(): RedisCacheConfiguration {
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(60))
            .disableCachingNullValues()
            .serializeValuesWith(SerializationPair.fromSerializer<Any>(GenericJackson2JsonRedisSerializer()))
    }

    @Bean
    fun redisCacheManagerBuilderCustomizer(): RedisCacheManagerBuilderCustomizer {
        return RedisCacheManagerBuilderCustomizer { builder: RedisCacheManagerBuilder ->
            builder
                .withCacheConfiguration(
                    "user",
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10))
                )
                .withCacheConfiguration(
                    "tokenValidity",
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10))
                )
                .withCacheConfiguration(
                    "tokenEmail",
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10))
                )
                .withCacheConfiguration(
                    "tokenClaims",
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10))
                )
                .withCacheConfiguration(
                    "employee",
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10))
                )
        }
    }
}
