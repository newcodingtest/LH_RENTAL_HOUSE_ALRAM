package org.alram.lh.rental.service.scheduler

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.gson.Gson
import mu.KotlinLogging
import org.alram.lh.rental.domain.ApiResponse
import org.alram.lh.rental.utils.LhApiParameters
import org.json.JSONArray
import org.json.JSONObject
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


@TestPropertySource(properties = ["app.scheduling.enable=false"]) //테스트 환경에서는 스케쥴러를 OFF 해둔다.
@SpringBootTest
class LhOpenApiCallTest @Autowired constructor(
    val lhOpenApiServiceImpl: LhOpenApiCall,
    val restTemplate: RestTemplate,
    val objectMapper: ObjectMapper,
    val gson: Gson

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
        val cityCode = LhApiParameters.광주.code
        val kindOfHouse = LhApiParameters.임대주택.code

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
            .queryParam("CNP_CD",LhApiParameters.광주.code)
            .queryParam("UPP_AIS_TP_CD",LhApiParameters.임대주택.code)
            .build(true)

        println("url 경로: "+uri)

        println(restTemplate
                    .getForEntity(uri.toUri(), String::class.java)
                .body)
    }

    @Test
    fun `restTemplate으로 비동기 처리를 할 수 있다`() {
        //given
        val executorService = Executors.newFixedThreadPool(5)
        val baseUrl = "http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1"
        val cityCode1 = LhApiParameters.광주.code
        val cityCode2 = LhApiParameters.서울.code
        val cityCode3 = LhApiParameters.대전.code
        val cityCode4 = LhApiParameters.대구.code
        val cityCode5 = LhApiParameters.부산.code
        val kindOfHouse = LhApiParameters.임대주택.code

        var panSS = URLEncoder
            .encode("공고중", StandardCharsets.UTF_8.toString())

        var uri1 = UriComponentsBuilder
            .fromHttpUrl(baseUrl)
            .queryParam("serviceKey", encodeKey)
            .queryParam("PAN_SS", panSS)
            .queryParam("CNP_CD", cityCode1)
            .queryParam("UPP_AIS_TP_CD", kindOfHouse)
            .build(true)
        var uri2 = UriComponentsBuilder
            .fromHttpUrl(baseUrl)
            .queryParam("serviceKey", encodeKey)
            .queryParam("PAN_SS", panSS)
            .queryParam("CNP_CD", cityCode2)
            .queryParam("UPP_AIS_TP_CD", kindOfHouse)
            .build(true)
        var uri3 = UriComponentsBuilder
            .fromHttpUrl(baseUrl)
            .queryParam("serviceKey", encodeKey)
            .queryParam("PAN_SS", panSS)
            .queryParam("CNP_CD", cityCode3)
            .queryParam("UPP_AIS_TP_CD", kindOfHouse)
            .build(true)
        var uri4 = UriComponentsBuilder
            .fromHttpUrl(baseUrl)
            .queryParam("serviceKey", encodeKey)
            .queryParam("PAN_SS", panSS)
            .queryParam("CNP_CD", cityCode4)
            .queryParam("UPP_AIS_TP_CD", kindOfHouse)
            .build(true)
        var uri5 = UriComponentsBuilder
            .fromHttpUrl(baseUrl)
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
        }

    fun testPrint(tmp: String?){
        log.info { "test ${tmp}" }
    }

    @Test
    fun `response를 json 타입으로 받을 수 있다`(){
        //given
        val baseUrl = "http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1"
        val serviceKey = encodeKey
        val cityCode = LhApiParameters.광주.code
        val kindOfHouse = LhApiParameters.임대주택.code

        //when
        var target = lhOpenApiServiceImpl.searchHouse(baseUrl,
                                        serviceKey,
                                        cityCode,
                                        kindOfHouse).body?:""

        val jsonArray = JSONArray(target)
        val expected = jsonArray.getJSONObject(1).get("dsList").toString()
        if (expected.length<3){
            println("데이터가 없습니다.")
            return
        }

        val jsonObj : JSONObject= jsonArray.getJSONObject(1)
        val strList = jsonObj.get("dsList").toString()
        val jsonNode = objectMapper.readTree(strList)

        //then
        var resultList = ArrayList<String>()
        jsonNode.forEach {
            val apiResponse: ApiResponse = objectMapper.readValue(it.toString(),
                ApiResponse::class.java)
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);  // list deserialization 기능 활성화
            resultList.add(gson.toJson(apiResponse))
        }

        println("${resultList}")

    }

    }



