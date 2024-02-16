package org.alram.lh.rental.infra.entity

import jakarta.persistence.*
import org.alram.lh.rental.domain.LhNotice

@Entity
@Table(name = "lhnotice")
class LhNoticeEntity(

    @Id
    @Column(name = "code")
    var code: Long,

    @Lob
    @Column(name = "content", length = 100000)
    var content: String
): BaseEntity()




