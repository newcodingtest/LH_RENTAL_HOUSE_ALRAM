package org.alram.lh.rental.infra.impl

import lombok.RequiredArgsConstructor
import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.infra.LhRedisRepository
import org.alram.lh.rental.infra.entity.RedisEntity
import org.alram.lh.rental.service.port.LhRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
@RequiredArgsConstructor
class LhRedisRepositoryImpl(
    private val redisTemplate: RedisTemplate<String, String>,
    private val lhRedisRepository: LhRedisRepository
): LhRepository {
    override fun save(lhNotice: LhNotice) {
        TODO("Not yet implemented")
        lhRedisRepository.save(RedisEntity(lhNotice.id, lhNotice.content))
    }

    override fun findById(id: Long): LhNotice{
        TODO("Not yet implemented")
        return lhRedisRepository.findById(id).get().toModel()
    }
}