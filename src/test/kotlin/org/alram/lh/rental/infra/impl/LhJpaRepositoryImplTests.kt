package org.alram.lh.rental.infra.impl

import org.alram.lh.rental.domain.LhNotice
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class LhJpaRepositoryImplTests @Autowired constructor(
    val lhJpaRepositoryImpl : LhJpaRepositoryImpl
) {

    @Test
    fun 데이터를_생성할_수_있다(){
        //given
        val given = LhNotice(code = 1111,
                            content = "test")

        //when
        lhJpaRepositoryImpl.create(given)
        val expected = lhJpaRepositoryImpl.searchByCode(given.code)

        //then
        Assertions.assertEquals(expected.content, given.content)
    }

    @Test
    fun code로_조회할_수_있다(){
        //given
        val code = 2906L

        //when
        val expected = lhJpaRepositoryImpl.searchByCode(code)

        Assertions.assertNotNull(expected)

        print(expected)
    }
}