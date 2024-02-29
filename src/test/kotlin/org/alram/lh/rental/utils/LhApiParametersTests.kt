package org.alram.lh.rental.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LhApiParametersTests {

    @Test
    fun `of 메서드로 enum 내부 문자열 형태를 숫자열 형태로 변환이 가능하다`(){
        val given = "광주"

        Assertions.assertEquals("29", LhApiParameters.getCodeFromName(given))
    }

    @Test
    fun `메서드로 enum 내부 숫자 형태를 문자열 형태로 변환이 가능하다`(){
        val given = "29"

        Assertions.assertEquals("광주", LhApiParameters.getNameFromCode(given))
    }

    @Test
    fun `getCityList로 enum의 숫자 리스트를 출력할 수 있다`(){
        Assertions.assertEquals(23, LhApiParameters.getCityList()
                                                            .size)
    }

}