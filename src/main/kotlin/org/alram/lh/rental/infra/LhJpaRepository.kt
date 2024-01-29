package org.alram.lh.rental.infra

import org.alram.lh.rental.infra.entity.LhNoticeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface LhJpaRepository : JpaRepository<LhNoticeEntity, Long>