package org.alram.lh.rental.controller.port

import org.alram.lh.rental.domain.LhNotice
import org.springframework.cache.annotation.Cacheable

interface LhOpenApiService {
    fun searchNotice(cityCode: String, kindOfHouse: String): LhNotice
}