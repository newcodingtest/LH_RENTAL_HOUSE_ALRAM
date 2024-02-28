package org.alram.lh.rental.service.port

import org.alram.lh.rental.domain.LhNotice

interface LhBatchRepository {
    fun save(notice: List<LhNotice>)
}