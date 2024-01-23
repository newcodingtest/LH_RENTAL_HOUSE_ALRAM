package org.alram.lh.rental.infra.impl

import lombok.RequiredArgsConstructor
import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.infra.LhJpaRepository
import org.alram.lh.rental.infra.entity.LhNoticeEntity
import org.alram.lh.rental.infra.entity.RedisEntity
import org.alram.lh.rental.service.port.LhRepository
import org.springframework.stereotype.Repository

@Repository
@RequiredArgsConstructor
class LhJpaRepositoryImpl(
    private val lhJpaRepository: LhJpaRepository
): LhRepository {
    override fun save(lhNotice: LhNotice) {
        lhJpaRepository.save(LhNoticeEntity.fromModel(lhNotice))
    }

    override fun findById(id: Long): LhNotice {
        return lhJpaRepository.findById(id).get().toModel()
    }


}