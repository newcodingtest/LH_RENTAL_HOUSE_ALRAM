package org.alram.lh.rental.infra.impl

import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.infra.redis.LhRedisRepository
import org.alram.lh.rental.infra.entity.RedisEntity
import org.alram.lh.rental.service.port.LhRepository
import org.springframework.context.annotation.Lazy
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository("lhRedisRepositoryImpl")
class LhRedisRepositoryImpl(
    /*private val redisTemplate: RedisTemplate<String, String>,*/
    @Lazy val lhRedisRepository: LhRedisRepository

): LhRepository {
    override fun create(lhNotice: LhNotice) {
        //redisTemplate.
        lhRedisRepository.save(RedisEntity(code = lhNotice.code,
                                        content = lhNotice.content))
    }

    override fun searchByCode(code: Long): LhNotice{
        val entity = lhRedisRepository.findByCode(code)

        return LhNotice(code = entity.code,
                        content = entity.content)
    }
}