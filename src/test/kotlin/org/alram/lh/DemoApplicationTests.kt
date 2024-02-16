package org.alram.lh

import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean

@SpringBootTest
class DemoApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Bean
    fun gson(): Gson {
        return Gson()
    }

}
