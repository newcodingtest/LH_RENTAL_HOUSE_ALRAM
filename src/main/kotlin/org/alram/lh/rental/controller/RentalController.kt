package org.alram.lh.rental.controller

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.client.RestTemplate

@RequiredArgsConstructor
@Controller
class RentalController(
    private val restTemplate: RestTemplate
) {

    @GetMapping( "/publichouse")
    fun searchPublicRentalHouse(): ResponseEntity<String>{

        /***
         *
         * todo
         * lh api 호출
         */


        var test: String? = "";

        return ResponseEntity.ok().body(test);
    }
}