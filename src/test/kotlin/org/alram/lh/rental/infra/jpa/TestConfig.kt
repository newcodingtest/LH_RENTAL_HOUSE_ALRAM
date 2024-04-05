package org.alram.lh.rental.infra.jpa

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfig {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

   @Bean
   fun jpaQueryFactory(): JPAQueryFactory{
       return JPAQueryFactory(entityManager)
   }

}