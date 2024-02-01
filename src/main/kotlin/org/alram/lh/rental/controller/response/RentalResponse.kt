package org.alram.lh.rental.controller.response

import org.alram.lh.rental.domain.LhNotice


class RentalResponse(
     val cityName: String,
     val content: String,
) {

    companion object {
        fun fromModel(lhNotice: LhNotice): RentalResponse{
            return RentalResponse(cityName = lhNotice.code.toString(),
                content = lhNotice.content)
        }
    }

}