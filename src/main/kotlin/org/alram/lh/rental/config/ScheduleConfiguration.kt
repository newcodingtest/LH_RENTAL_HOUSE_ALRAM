package org.alram.lh.rental.config

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@ConditionalOnProperty(value = ["app.scheduling.enable"], havingValue = "true", matchIfMissing = true)
@EnableScheduling
@Configuration
class ScheduleConfiguration {

    @Bean
    fun modelMapper() : ModelMapper {
        return ModelMapper()
    }
}