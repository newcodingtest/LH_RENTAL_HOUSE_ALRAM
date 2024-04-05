package org.alram.lh.rental.api.response

import org.alram.lh.rental.domain.LhNotice


class RentalResponse(
     val cityName: String,
     val content: String,
     val cnt: Int,
     val city: String
) {

    companion object {
        fun fromModel(lhNotice: LhNotice): RentalResponse{
            return RentalResponse(cityName = lhNotice.code.toString(),
                content = lhNotice.content,
                cnt = lhNotice.cnt,
                city = lhNotice.city)
        }
    }

}