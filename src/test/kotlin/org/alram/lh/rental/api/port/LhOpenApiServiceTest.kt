package org.alram.lh.rental.api.port

import org.alram.lh.rental.service.LhOpenApiServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(properties = ["app.scheduling.enable=false"])
class LhOpenApiServiceTest @Autowired constructor(
    val LhOpenApiServiceImpl: LhOpenApiServiceImpl
){

    @Test
    fun `검색어로 관련된 공고문을 찾을 수 있다`(){

        //given
        val words = "경기도"

        //when
        val expected = LhOpenApiServiceImpl.searchNotices(words)

        //then
        Assertions.assertEquals(words, expected.city)
    }
}