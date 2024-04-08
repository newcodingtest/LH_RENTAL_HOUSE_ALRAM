package org.alram.lh.rental.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@AutoConfigureMockMvc
@TestPropertySource(properties = ["app.scheduling.enable=false"])
@SpringBootTest
class RentalControllerTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper


    @Test
    fun `주택 공고문을 조회할 수 있다`(){
        //given
        val url = "/public/house"

        //when
        val resultActions = mockMvc.perform(
            get(url)
                .param("cityCode", "29")
                .param("kindOfHouse", "06")
                .contentType(MediaType.APPLICATION_JSON)
        )

        //then
        resultActions
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andDo (print())
    }

    @Test
    fun `유효성에 맞기 않는 파라미터로 주택 공고문 조회시 에러이유와 에러코드가 반환되며 실패한다`(){
        //given
        val url = "/public/house"
        val errorMsg = "데이터가 존재하지 않습니다."
        val inValidCityCode = "291"

        //when
        val resultActions = mockMvc.perform(
            get(url)
                .param("cityCode", inValidCityCode)
                .param("kindOfHouse", "06")
                .contentType(MediaType.APPLICATION_JSON)
        )

        //then
        resultActions
            .andExpect(jsonPath("$.reason", equalTo(errorMsg)))
            .andExpect(jsonPath("$.code", equalTo(HttpStatus.NOT_FOUND.value())))
            .andDo (print())
    }

    @Test
    fun `검색어 관련 공고문을 찾을 수 있다`(){
        //given
        val words = "경기도"
        var url = "/public/house/search"

        //when
        val resultActions = mockMvc.perform(
            get(url)
                .param("search", words)
                .contentType(MediaType.APPLICATION_JSON)
        )

        //then
        resultActions
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andDo (print())
    }
}