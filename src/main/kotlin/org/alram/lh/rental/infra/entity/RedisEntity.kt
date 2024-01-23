package org.alram.lh.rental.infra.entity

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import org.alram.lh.rental.domain.LhNotice
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("notice")
class RedisEntity(
    @Id
    val id: Long,
    val content: String,

){
    fun toModel():LhNotice {
        return LhNotice(id, content);
    }
}