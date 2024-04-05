package org.alram.lh.rental.api

import mu.KotlinLogging
import org.alram.lh.rental.api.port.LhOpenApiService
import org.alram.lh.rental.api.response.RentalResponse
import org.alram.lh.rental.utils.LhApiParameters
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@CrossOrigin(origins = ["*"])
@Controller
class RentalController(
    private val lhOpenApiService: LhOpenApiService
) {
    val log = KotlinLogging.logger {}
    @GetMapping( value = ["/public/house"],
                produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun searchPublicRentalHouse(@RequestParam cityCode: String,
                                @RequestParam kindOfHouse: String): ResponseEntity<RentalResponse> {

        log.info { "request: ${cityCode}, ${kindOfHouse}" }
        return ResponseEntity.ok()
                            .body(RentalResponse
                                        .fromModel(lhOpenApiService
                                                    .searchNotice(cityCode, kindOfHouse)));
    }

    @GetMapping( value = ["/public/house/v1"],
                produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getNotice(@RequestParam cityName: String,
                                @RequestParam kindOfHouseName: String): ResponseEntity<RentalResponse> {

        log.info { "request: ${cityName}, ${kindOfHouseName}" }
        return ResponseEntity.ok()
                            .body(RentalResponse
                                    .fromModel(lhOpenApiService
                                                .searchNotice(LhApiParameters
                                                                .getCodeFromName(cityName), LhApiParameters
                                                                                                        .getCodeFromName(kindOfHouseName))));
    }

    /**
     * TODO: 검색 api
     *
     * */
    @GetMapping( value =["/public/house/search"],
                produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun search(@RequestParam content :String): ResponseEntity<RentalResponse> {
        //todo

        return ResponseEntity.ok(null);

    }

}