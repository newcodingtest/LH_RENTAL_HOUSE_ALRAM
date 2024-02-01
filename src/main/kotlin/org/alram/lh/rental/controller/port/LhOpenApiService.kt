package org.alram.lh.rental.controller.port

import org.alram.lh.rental.domain.LhNotice

interface LhOpenApiService {
    fun searchNotice(cityCode: String, kindOfHouse: String): LhNotice
}