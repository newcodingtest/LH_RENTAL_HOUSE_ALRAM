package org.alram.lh.rental.infra

import org.alram.lh.rental.service.LhOpenApiServiceImpl
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LhJpaRepositoryTest @Autowired constructor(
    val lhJpaRepository: LhJpaRepository
        ) {

    @Test
    fun jpa_create_test(){

    }

}