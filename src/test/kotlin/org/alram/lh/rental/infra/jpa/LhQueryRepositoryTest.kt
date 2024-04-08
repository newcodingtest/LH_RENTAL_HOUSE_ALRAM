package org.alram.lh.rental.infra.jpa

import com.querydsl.jpa.impl.JPAQueryFactory
import org.alram.lh.rental.infra.entity.QLhNoticeEntity
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("local")
@DataJpaTest
@Import(TestConfig::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LhQueryRepositoryTest {

    @Autowired
    private lateinit var jpaQueryFactory: JPAQueryFactory

    @Test
    fun searchTest(){
        //given
        val lhNotice: QLhNoticeEntity = QLhNoticeEntity.lhNoticeEntity
        val words = "경기";

        //when
        var expected = jpaQueryFactory.selectFrom(lhNotice)
            .where(lhNotice.content.contains(words).or(lhNotice.city.contains(words)))
            .fetchOne()

        //then
        Assertions.assertEquals(expected.city, words)
        println(expected)
    }
}