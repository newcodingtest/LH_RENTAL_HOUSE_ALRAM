package org.alram.lh.rental.service.scheduler

import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.stream.Collectors


@TestPropertySource(properties = ["app.scheduling.enable=false"])
@SpringBootTest
class LhOpenApiCallTest @Autowired constructor(
    val lhOpenApiServiceImpl: LhOpenApiCall,
    val restTemplate: RestTemplate

) {
    val log = KotlinLogging.logger {}

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

    @Test
    fun `병렬적으로 restTemplate을 요청할 수 있다1`() {
        //given
        val executorService = Executors.newFixedThreadPool(5)
        val baseUrl = "http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1"
        val serviceKey = encodeKey
        val cityCode1 = "29"
        val cityCode2 = "11"
        val cityCode3 = "42"
        val cityCode4 = "43"
        val cityCode5 = "44"
        val kindOfHouse = "06"

        var panSS = URLEncoder
            .encode("공고중", StandardCharsets.UTF_8.toString())

        var uri1 = UriComponentsBuilder
            .fromHttpUrl("http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1")
            .queryParam("serviceKey", encodeKey)
            .queryParam("PAN_SS", panSS)
            .queryParam("CNP_CD", cityCode1)
            .queryParam("UPP_AIS_TP_CD", kindOfHouse)
            .build(true)
        var uri2 = UriComponentsBuilder
            .fromHttpUrl("http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1")
            .queryParam("serviceKey", encodeKey)
            .queryParam("PAN_SS", panSS)
            .queryParam("CNP_CD", cityCode2)
            .queryParam("UPP_AIS_TP_CD", kindOfHouse)
            .build(true)
        var uri3 = UriComponentsBuilder
            .fromHttpUrl("http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1")
            .queryParam("serviceKey", encodeKey)
            .queryParam("PAN_SS", panSS)
            .queryParam("CNP_CD", cityCode3)
            .queryParam("UPP_AIS_TP_CD", kindOfHouse)
            .build(true)
        var uri4 = UriComponentsBuilder
            .fromHttpUrl("http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1")
            .queryParam("serviceKey", encodeKey)
            .queryParam("PAN_SS", panSS)
            .queryParam("CNP_CD", cityCode4)
            .queryParam("UPP_AIS_TP_CD", kindOfHouse)
            .build(true)
        var uri5 = UriComponentsBuilder
            .fromHttpUrl("http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1")
            .queryParam("serviceKey", encodeKey)
            .queryParam("PAN_SS", panSS)
            .queryParam("CNP_CD", cityCode5)
            .queryParam("UPP_AIS_TP_CD", kindOfHouse)
            .build(true)

        var list : List<UriComponents> = listOf(uri1, uri2, uri3, uri4, uri5)

        var futures = list.stream()
            .map { CompletableFuture.supplyAsync({
                var tmp = restTemplate.getForEntity(it.toUri(),String::class.java)
                log.info { it.toUri() }
                tmp.body
            }, executorService).thenAcceptAsync({
                testPrint(it)
            },executorService)  }.toList()

        //작업종료
         CompletableFuture.allOf(*futures.toTypedArray()).join()



//        var futures1 = list.stream()
//            .map { CompletableFuture.supplyAsync({
//                var tmp = restTemplate.getForEntity(it.toUri(),String::class.java)
//                log.info { it.toUri() }
//                testPrint(tmp.body)
//                //tmp.body
//            }, executorService) }.toList()
//
//        CompletableFuture.allOf(*futures1.toTypedArray()).join()
        }

    fun testPrint(tmp: String?){
        log.info { "test ${tmp}" }
    }

    }



