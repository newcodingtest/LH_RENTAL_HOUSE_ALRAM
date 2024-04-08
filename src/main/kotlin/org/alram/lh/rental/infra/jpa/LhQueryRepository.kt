package org.alram.lh.rental.infra.jpa

import com.querydsl.jpa.impl.JPAQueryFactory
import org.alram.lh.rental.infra.entity.LhNoticeEntity
import org.alram.lh.rental.infra.entity.QLhNoticeEntity
import org.springframework.stereotype.Repository
import java.util.NoSuchElementException

@Repository
class LhQueryRepository(
    private val queryFactory: JPAQueryFactory
)  {
    val lhNotice: QLhNoticeEntity = QLhNoticeEntity.lhNoticeEntity

    fun searchNoticeByContentAndCity(seach: String): LhNoticeEntity {
        return queryFactory.selectFrom(lhNotice)
            .where(lhNotice.content.contains(seach).and(lhNotice.city.contains(seach)))
            .fetchOne()?:throw NoSuchElementException();
    }
}