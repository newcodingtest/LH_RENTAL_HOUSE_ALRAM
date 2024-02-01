package org.alram.lh.rental.infra.impl

import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.infra.impl.LhRedisRepositoryImpl
import org.alram.lh.rental.infra.redis.LhRedisRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@TestPropertySource(properties = ["app.scheduling.enable=false"])
@Transactional
class LhRedisRepositoryImplTests @Autowired constructor(
    val lhRedisRepositoryImpl: LhRedisRepositoryImpl,
    var lhRedisRepository: LhRedisRepository
){

    @Test
    fun create_로_redis에_값을_생성할_수_있다(){
        //given
        val given = LhNotice(code = 1L,
                            content = "update1")

        //when
        lhRedisRepositoryImpl.create(given)

        val expected = lhRedisRepositoryImpl.searchByCode(given.code)

        //then
        Assertions.assertEquals(expected.content, given.content)
    }

    @Test
    fun code로_데이터를_조회할_수_있다(){
        //given
        val given = LhNotice(code = 1L,
            content = "test")
        lhRedisRepositoryImpl.create(given)

        //when
        val expected = lhRedisRepositoryImpl.searchByCode(given.code)

        //then
        Assertions.assertEquals(given.content, expected.content)
    }

    @Test
    fun 존재하지_않는_code로_조회할때_NoSuchElementException_이_된다(){
        //given
        val code = 9999L
        //when-then
        Assertions.assertThrows(NoSuchElementException::class.java){
            lhRedisRepositoryImpl.searchByCode(code)
        }
    }

    @Test
    fun 같은code의_새로운_content를_생성할때_기존_데이터가_존재하면_Update된다(){
        //given
        val origin = LhNotice(code = 1L,
            content = "origin")
        val update = LhNotice(code = 1L,
            content = "update")

        //when1
        lhRedisRepositoryImpl.create(origin)
        val originExpected = lhRedisRepositoryImpl.searchByCode(origin.code)
        //then1
        Assertions.assertEquals(origin.content, originExpected.content)

        //when2
        lhRedisRepositoryImpl.create(update)
        val updateExcepted = lhRedisRepositoryImpl.searchByCode(update.code)
        //then2
        Assertions.assertEquals(update.content, updateExcepted.content)
        Assertions.assertNotEquals(updateExcepted.content, originExpected.content)
    }
}