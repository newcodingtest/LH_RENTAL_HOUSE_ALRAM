package org.alram.lh.rental.infra.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("notice")
class RedisEntity(
    @Id
    var id: Long? = null,

    @Indexed
    var code: Long ,
    var content: String,

)