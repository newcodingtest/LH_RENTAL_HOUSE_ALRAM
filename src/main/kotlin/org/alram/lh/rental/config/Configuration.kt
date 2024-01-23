package org.alram.lh.rental.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestTemplate
import java.time.Duration

@EnableScheduling
@EnableCaching
@Configuration
class Configuration: CachingConfigurerSupport() {

//    @Bean
//    fun restTemplate(): RestTemplate {
//        return RestTemplate()
//    }

    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()

    @Value("\${spring.data.redis.host}")
    lateinit var host: String

    @Value("\${spring.data.redis.port}")
    var port: Int = 6379

    @Bean
    fun lettuceConnectionFactory(): LettuceConnectionFactory {
        val lettuceClientConfiguration = LettuceClientConfiguration.builder()
            .commandTimeout(Duration.ZERO)
            .shutdownTimeout(Duration.ZERO)
            .build()

        val redisStandaloneConfiguration = RedisStandaloneConfiguration(host, port)
        return LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        val template = RedisTemplate<ByteArray, ByteArray>()
        template.keySerializer = StringRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.valueSerializer = StringRedisSerializer()
        template.setConnectionFactory(lettuceConnectionFactory())
        return template
    }

    @Bean
    override fun cacheManager(): CacheManager {
        val builder = RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(lettuceConnectionFactory())

        val configuration = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(1))
        builder.cacheDefaults(configuration)

        return builder.build()
    }
}