package org.alram.lh.rental.service.port

import org.alram.lh.rental.domain.LhNotice

interface LhRepository {
    fun save(lhNotice: LhNotice)
    fun findById(id: Long): LhNotice

}