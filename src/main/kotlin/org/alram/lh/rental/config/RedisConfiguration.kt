package org.alram.lh.rental.config

import org.alram.lh.rental.domain.ApiResponse
import org.alram.lh.rental.domain.LhNotice
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
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestTemplate
import java.time.Duration

@EnableScheduling
@EnableCaching
@EnableRedisRepositories(basePackages = ["org.alram.lh.rental.infra.redis"])
@Configuration
class RedisConfiguration: CachingConfigurerSupport() {

//    @Bean
//    fun restTemplate(): RestTemplate {
//        return RestTemplate()
//    }

    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()

    @Value("\${spring.data.redis.host}")

    lateinit var host: String
    @Value("\${spring.data.redis.password}")
    lateinit var password: String

    @Value("\${spring.data.redis.port}")
    var port: Int = 6379

    @Bean
    fun lettuceConnectionFactory(): LettuceConnectionFactory {
        val lettuceClientConfiguration = LettuceClientConfiguration.builder()
//            .commandTimeout(Duration.ZERO)
//            .shutdownTimeout(Duration.ZERO)
            .build()

        val redisStandaloneConfiguration = RedisStandaloneConfiguration(host, port)
        redisStandaloneConfiguration.setPassword(password)
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
            //.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(Jackson2JsonRedisSerializer(LhNotice::class.java)))
            .serializeValuesWith(RedisSerializationContext
                .SerializationPair
                .fromSerializer(GenericJackson2JsonRedisSerializer()))
            .entryTtl(Duration.ofHours(3))

        builder.cacheDefaults(configuration)

        return builder.build()
    }
}