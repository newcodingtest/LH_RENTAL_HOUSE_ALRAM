package org.alram.lh.rental.infra.impl

import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.infra.redis.LhRedisRepository
import org.alram.lh.rental.infra.entity.RedisEntity
import org.alram.lh.rental.service.port.LhRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Repository

@Repository("lhRedisRepositoryImpl")
class LhRedisRepositoryImpl(
//    private val redisTemplate: RedisTemplate<String, String>,
    @Lazy val lhRedisRepository: LhRedisRepository

): LhRepository {
    override fun create(lhNotice: LhNotice) {
        //redisTemplate.
        lhRedisRepository.save(RedisEntity(code = lhNotice.code,
                                        content = lhNotice.content,
                                        cnt = lhNotice.cnt,
                                        city = lhNotice.city
                                        )
                                )
    }

    override fun searchByCode(code: Long): LhNotice{
        val entity = lhRedisRepository
                                    .findById(code)
                                    .get()?:throw NoSuchElementException("not exist data")

        return LhNotice(code = entity.code,
                        content = entity.content,
                        cnt = entity.cnt,
                        city = entity.city
                        )
    }

    override fun update(lhNotice: LhNotice) {
        TODO("Not yet implemented")



    }


}