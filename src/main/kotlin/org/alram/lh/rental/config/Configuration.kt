package org.alram.lh.rental.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class Configuration {

//    @Bean
//    fun restTemplate(): RestTemplate {
//        return RestTemplate()
//    }

    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()
}