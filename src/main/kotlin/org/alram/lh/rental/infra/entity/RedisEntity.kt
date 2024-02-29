package org.alram.lh.rental.infra.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("notice")
class RedisEntity(
//    @Id
//    var id: Long? = null,

    @Id
    var code: Long,
    var content: String,
    var cnt: Int,

)