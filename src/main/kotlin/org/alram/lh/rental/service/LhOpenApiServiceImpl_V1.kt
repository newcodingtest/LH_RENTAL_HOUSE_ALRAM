package org.alram.lh.rental.service

import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


/**
 * redis 고려사항
 * 1. 용량, 2.데이터 만료 정책, 3.키 네이밍 규칙
 * */
@Service
@RequiredArgsConstructor
class LhOpenApiServiceImpl_V1(
     private val redisTemplate: RedisTemplate<String, String>,
     private val restTemplate: RestTemplate
) {
     val log = KotlinLogging.logger {}


     fun searchNotice(cityCode: String, kindOfHouse: String): String {
          
          /**
           * todo:
           *  1. Redis 조회
           *  2. 데이터 없으면 DB조회
           *  3. DB데이터 Redis에 갱신 (동기화)
           *
           * */
          return ""
     }
}