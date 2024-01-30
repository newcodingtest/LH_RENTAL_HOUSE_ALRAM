package org.alram.lh.rental.infra.entity

import jakarta.persistence.*
import org.alram.lh.rental.domain.LhNotice

@Entity
@Table(name = "lhnotice")
class LhNoticeEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "code")
    var code: Long,

    @Column(name = "content")
    var content: String
)




