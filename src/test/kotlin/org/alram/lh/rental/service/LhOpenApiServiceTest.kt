package org.alram.lh.rental.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@SpringBootTest
class LhOpenApiServiceTest @Autowired constructor(
    val lhOpenApiServiceImpl: LhOpenApiCall,
    val restTemplate: RestTemplate

) {

    @Value("\${lh.servicekey.decode}")
    lateinit var decodeKey: String

    @Value("\${lh.servicekey.encode}")
    lateinit var encodeKey: String

    /**
     *
     * http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1?serviceKey=OPrzGNbeGKWMffAEpVmOkqzrC9FjbuvyHR%2F3DWvQFtl%2BrPp0mmSv8nR6y8j6pnEr3S6KQltHw%2FuSCU%2BevaeyKA%3D%3D
     * &PG_SZ=10
     * &PAGE=1
     * &PAN_SS=공고중
     * &CNP_CD=29
     * &UPP_AIS_TP_CD=06
     * */

    @Test
    fun 광주지역의_공고중인_공고문을_검색한다() {
        val baseUrl = "http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1"
        val serviceKey = encodeKey
        val cityCode = "29"
        val kindOfHouse = "06"

       println(lhOpenApiServiceImpl.searchHouse(baseUrl, serviceKey, cityCode, kindOfHouse))
    }

    @Test
    fun restTemplate과_UriComponentsBuilder로_get요청을_할_수_있다() {
         println("encode: "+encodeKey)

        var panSS = URLEncoder
            .encode("공고중", StandardCharsets.UTF_8.toString())

        var uri = UriComponentsBuilder
            .fromHttpUrl("http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1")
            .queryParam("serviceKey", encodeKey)
            .queryParam("PAN_SS", panSS)
            .queryParam("CNP_CD","29")
            .queryParam("UPP_AIS_TP_CD","06")
            .build(true)

        println("url 경로: "+uri)

        println(restTemplate
                    .getForEntity(uri.toUri(), String::class.java)
                .body)
    }


}