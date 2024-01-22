package org.alram.lh.rental.service

import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
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

     val panSS: String = URLEncoder
          .encode("공고중", StandardCharsets.UTF_8.toString())

     fun search(cityCode: String, kindOfHouse: String): String {
          val result = redisTemplate.opsForValue()
               .get(cityCode+kindOfHouse)?:""

          log.info { "get Redis value \n\n ${result}"}
          return result
     }




     fun searchHouse(baseUrl: String, encodeKey: String, cityCode: String, kindOfHouse: String): ResponseEntity<String> {
          var uri = UriComponentsBuilder
               .fromHttpUrl(baseUrl)
               .queryParam("serviceKey", encodeKey)
               .queryParam("PG_SZ", "10")
               .queryParam("PAGE", "1")
               .queryParam("PAN_SS", panSS)
               .queryParam("CNP_CD", cityCode)
               .queryParam("UPP_AIS_TP_CD",kindOfHouse)
               .build(true)

          return restTemplate.getForEntity(uri.toUri(), String::class.java)
     }
}