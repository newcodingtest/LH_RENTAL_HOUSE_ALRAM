package org.alram.lh.rental.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class LhOpenApiServiceTest @Autowired constructor(
    val lhOpenApiService: LhOpenApiService
) {

    /**
     *
     * http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1?serviceKey=OPrzGNbeGKWMffAEpVmOkqzrC9FjbuvyHR%2F3DWvQFtl%2BrPp0mmSv8nR6y8j6pnEr3S6KQltHw%2FuSCU%2BevaeyKA%3D%3D
     * &PG_SZ=10
     * &PAGE=1552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1?serviceKey=OPrzGNbeGKWMffAEpVmOkqzrC9FjbuvyHR%2F3DWvQFtl%2BrPp0mmSv8nR6y8j6pnEr3S6KQltHw%2FuSCU%2BevaeyKA%3D%3D&PG_SZ=10&PAGE=1&PAN_SS=공고중"
        val cityCode = "29"
        val kindOfHouse = "06"

        println(lhOpenApiService.searchHouse(baseUrl, cityCode, kindOfHouse))
    }
    * &PAN_SS=공고중
    * &CNP_CD=29
    * &UPP_AIS_TP_CD=06
    * */

    @Test
    fun 공고중인_공고문을_검색한다() {
        val baseUrl = "http://apis.data.go.kr/B
//    @Test
//    fun 공고중인_공고문을_검색한다1() {
//        val result : ResponseEntity<String> = restTemplate.getForObject("http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1?serviceKey=OPrzGNbeGKWMffAEpVmOkqzrC9FjbuvyHR%2F3DWvQFtl%2BrPp0mmSv8nR6y8j6pnEr3S6KQltHw%2FuSCU%2BevaeyKA%3D%3D&PG_SZ=10&PAGE=1&PAN_SS=공고중&")
//
//        print(result)
//    }
}