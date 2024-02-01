package org.alram.lh.rental.infra.redis

import org.alram.lh.rental.infra.entity.RedisEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface LhRedisRepository: CrudRepository<RedisEntity,Long> {
    fun findByCode(code: Long): RedisEntity?

    fun update(code: Long)

}
