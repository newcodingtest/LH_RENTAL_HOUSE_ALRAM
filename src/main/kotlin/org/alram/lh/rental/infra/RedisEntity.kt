package org.alram.lh.rental.infra

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
class RedisEntity(
    @Id
    val id: Long,
    val content: String,

)