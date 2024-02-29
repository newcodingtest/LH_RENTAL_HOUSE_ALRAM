package org.alram.lh.rental.service

import mu.KotlinLogging
import org.alram.lh.rental.controller.port.LhOpenApiService
import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.service.port.LhRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service


/**
 * redis 고려사항
 * 1. 용량, 2.데이터 만료 정책, 3.키 네이밍 규칙
 * */
@Service
class LhOpenApiServiceImpl(
     @Qualifier("lhRedisRepositoryImpl")
     private val redisRepository: LhRepository,
     @Qualifier("lhJpaRepositoryImpl")
     private val jpaRepository: LhRepository
): LhOpenApiService {

     val log = KotlinLogging.logger {}

     @Cacheable(cacheNames = ["noticeCache"],
                    key = "'notice:'+#cityCode +#kindOfHouse",
               cacheManager = "cacheManager")
     override fun searchNotice(cityCode: String, kindOfHouse: String): LhNotice {
          val code = (cityCode+kindOfHouse).toLong()

          var result = jpaRepository.searchByCode(code)

          redisRepository.create(LhNotice(code = result.code,
               content = result.content,
               cnt = result.cnt,
               city = result.city))
          return result

     }
}