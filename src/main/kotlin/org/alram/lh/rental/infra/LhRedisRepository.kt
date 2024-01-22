package org.alram.lh.rental.infra

import org.springframework.data.repository.CrudRepository

interface LhRedisRepository: CrudRepository<RedisEntity,Long>
