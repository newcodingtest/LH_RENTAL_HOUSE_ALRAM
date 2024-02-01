package org.alram.lh.rental.service.port

import org.alram.lh.rental.domain.LhNotice

interface LhRepository {
    fun create(lhNotice: LhNotice)
    fun searchByCode(code: Long): LhNotice

    fun update(lhNotice: LhNotice)

}