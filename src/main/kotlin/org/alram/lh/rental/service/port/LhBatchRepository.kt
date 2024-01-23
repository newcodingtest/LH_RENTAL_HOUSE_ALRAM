package org.alram.lh.rental.service.port

import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.infra.entity.RedisEntity

interface LhBatchRepository {
    fun save(notice: List<LhNotice>)
}