package org.alram.lh.rental.infra.impl

import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.infra.LhJpaRepository
import org.alram.lh.rental.infra.entity.LhNoticeEntity
import org.alram.lh.rental.service.port.LhRepository
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service


@Repository
class LhJpaRepositoryImpl(
    @Lazy val lhJpaRepository: LhJpaRepository
): LhRepository {

    override fun create(lhNotice: LhNotice) {
        lhJpaRepository.save(LhNoticeEntity().fromModel(lhNotice))
    }

    override fun searchById(id: Long): LhNotice {
        val lhNoticeEntity = lhJpaRepository.findById(id).orElseThrow{Exception("test")};

        return lhNoticeEntity.toModel();
    }
}