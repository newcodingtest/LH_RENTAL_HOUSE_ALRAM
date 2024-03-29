package org.alram.lh.rental.infra.impl

import mu.KotlinLogging
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
    val log = KotlinLogging.logger {}

    override fun create(lhNotice: LhNotice) {
        lhJpaRepository.save(LhNoticeEntity(code = lhNotice.code,
                                        content = lhNotice.content,
                                        cnt = lhNotice.cnt,
                                        city = lhNotice.city
                                        )
                            )
    }

    override fun searchByCode(id: Long): LhNotice {
        log.info { "use db" }
        val lhNoticeEntity = lhJpaRepository
                                .findByCode(id)?:throw NoSuchElementException()

        return LhNotice(code = lhNoticeEntity.code,
                    content = lhNoticeEntity.content,
                    cnt = lhNoticeEntity.cnt,
                    city = lhNoticeEntity.city
                        )
    }

    override fun update(lhNotice: LhNotice) {
        TODO("Not yet implemented")
    }
}