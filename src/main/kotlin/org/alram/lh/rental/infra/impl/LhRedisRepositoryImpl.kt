package org.alram.lh.rental.infra.impl

import lombok.RequiredArgsConstructor
import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.service.port.LhRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class LhRedisRepositoryImpl(
    private val redisTemplate: RedisTemplate<String, String>,

): LhRepository {
    override fun create(lhNotice: LhNotice) {
        TODO("Not yet implemented")
        //redisTemplate.
        //lhRedisRepository.save(RedisEntity(lhNotice.id, lhNotice.content))
    }

    override fun searchById(id: Long): LhNotice{
        TODO("Not yet implemented")
        //return lhRedisRepository.findById(id).get().toModel()
    }
}