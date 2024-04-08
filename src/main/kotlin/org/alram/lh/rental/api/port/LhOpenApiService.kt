package org.alram.lh.rental.api.port

import org.alram.lh.rental.api.response.RentalResponse
import org.alram.lh.rental.domain.LhNotice

interface LhOpenApiService {
    fun searchNotice(cityCode: String, kindOfHouse: String): LhNotice

    fun getNumberOfNotices(): Int

    fun searchNotices(search: String): LhNotice
}