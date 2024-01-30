package org.alram.lh.rental.infra.impl

import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.infra.impl.LhRedisRepositoryImpl
import org.alram.lh.rental.infra.redis.LhRedisRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class LhRedisRepositoryImplTests @Autowired constructor(
    val lhRedisRepositoryImpl: LhRedisRepositoryImpl,
    var lhRedisRepository: LhRedisRepository
){

    @Test
    fun create_로_redis에_값을_생성할_수_있다(){
        //given
        val given = LhNotice(code = 1,
                            content = "test")

        //when
        lhRedisRepositoryImpl.create(given)

        val expected = lhRedisRepositoryImpl.searchByCode(given.code)

        //then
        Assertions.assertEquals(expected.content, given.content)
    }

    @Test
    fun searchById_로_redis에_value를_찾을_수_있다(){
        //given
        val given = LhNotice(code = 1,
            content = "test")
        lhRedisRepositoryImpl.create(given)

        //when
        val expected = lhRedisRepositoryImpl.searchByCode(1)

        //then
        Assertions.assertEquals(given.content, expected.content)
    }
}