package org.alram.lh.rental.infra.impl

import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.infra.jpa.LhJpaRepository
import org.alram.lh.rental.infra.entity.LhNoticeEntity
import org.alram.lh.rental.service.port.LhRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Repository
import java.lang.Exception

//Since Spring v4.3: If only one constructor exists, then we don’t need to use @Autowired
@Repository("lhJpaRepositoryImpl")
class LhJpaRepositoryImpl(
    @Lazy val lhJpaRepository: LhJpaRepository
): LhRepository {

    override fun create(lhNotice: LhNotice) {
        lhJpaRepository.save(LhNoticeEntity(code = lhNotice.code,
                                        content = lhNotice.content))
    }

    override fun searchByCode(id: Long): LhNotice {
        val lhNoticeEntity = lhJpaRepository
                                .findByCode(id)?:throw NoSuchElementException()

        return LhNotice(code = lhNoticeEntity.code,
                    content = lhNoticeEntity.content)
    }

    override fun update(lhNotice: LhNotice) {
        TODO("Not yet implemented")
    }
}