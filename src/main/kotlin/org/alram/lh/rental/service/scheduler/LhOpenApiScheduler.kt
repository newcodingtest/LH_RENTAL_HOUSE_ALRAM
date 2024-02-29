package org.alram.lh.rental.service.scheduler

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import mu.KotlinLogging
import org.alram.lh.rental.domain.ApiResponse
import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.service.port.LhBatchRepository
import org.alram.lh.rental.service.port.LhRepository
import org.alram.lh.rental.utils.LhApiParameters
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.configurationprocessor.json.JSONArray
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

private val logger = KotlinLogging.logger {}

@Component
class LhOpenApiScheduler(
    private val lhOpenApiServiceImpl: LhOpenApiCall,
    private val lhBatchRepository: LhBatchRepository,
    private val objectMapper: ObjectMapper,
    private val gson: Gson,
    @Qualifier("lhJpaRepositoryImpl")
    private val lhRepository: LhRepository,
    ) {
    private val notices: ArrayList<LhNotice> = ArrayList()
    @Value("\${lh.servicekey.encode}")
    lateinit var encodeKey: String
    @Value("\${lh.baseUrl}")
    lateinit var baseUrl: String

    //db batch insert

//    private fun createBatch(){
//        notices.clear()
//
//        val value  = lhOpenApiServiceImpl.searchHouse(baseUrl,
//            encodeKey,
//            LhApiParameters.광주.code,
//            LhApiParameters.임대주택.code).body?:""
//
//        logger.info { "request ${value}"}
//        val notice = LhNotice(code = (LhApiParameters.광주.code+LhApiParameters.임대주택.code).toLong(),
//            content = value)
//
//        logger.info { "notice ${notice}" }
//        notices.add(notice)
//
//        lhBatchRepository.save(notices)
//    }

    @Scheduled(cron = "\${lh.cron}")
    private fun createParallelAsync(){
        val executorService = Executors.newFixedThreadPool(5)
        var list : List<String> = LhApiParameters.getCityList()

        var futures = list.stream()
            .map { CompletableFuture.supplyAsync({
                var apiResult = lhOpenApiServiceImpl.searchHouse(baseUrl,
                    encodeKey,
                    it,
                    LhApiParameters.임대주택.code).body?:""
                logger.info { apiResult }
                var jsonList = toApiResponseModel(apiResult)

                LhNotice(code = (it+LhApiParameters.임대주택.code).toLong(),
                    content = jsonList.toString(),
                    cnt = jsonList.size,
                    city = LhApiParameters.getNameFromCode(it))
            }, executorService).thenAcceptAsync({
                //db저장
                lhRepository.create(it)
            },executorService)  }.toList()

        //작업종료
        CompletableFuture.allOf(*futures.toTypedArray()).join()
    }

    fun toApiResponseModel(apiResult: String): ArrayList<String>{
        var resultList = ArrayList<String>()
        var apiContentJsonArr: JSONArray
        return if(apiResult != ""){
            var apiContentBody = ""
            try {
                apiContentJsonArr = JSONArray(apiResult)
                apiContentBody = apiContentJsonArr.getJSONObject(1).get("dsList").toString()
            }catch (e: Exception){
                logger.error { e.message }
                return resultList
            }

            if (apiContentBody.length>=3){
                val jsonObj : JSONObject= apiContentJsonArr.getJSONObject(1)
                val strList = jsonObj.get("dsList").toString()
                val jsonNode = objectMapper.readTree(strList)

                //then
                jsonNode.forEach {
                    val apiResponse: ApiResponse = objectMapper.readValue(it.toString(),
                        ApiResponse::class.java)
                    objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);  // list deserialization 기능 활성화
                    resultList.add(gson.toJson(apiResponse))
                }
                resultList
            } else {
                resultList
            }

        } else {
            resultList
        }
    }

}