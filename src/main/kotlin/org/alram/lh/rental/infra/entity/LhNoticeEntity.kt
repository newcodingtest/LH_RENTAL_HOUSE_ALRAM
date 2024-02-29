package org.alram.lh.rental.infra.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault

@Entity
@Table(name = "lhnotice")
class LhNoticeEntity(

    @Id
    @Column(name = "code")
    var code: Long,

    @Lob
    @Column(name = "content", length = 100000)
    var content: String,

    @Column(name = "cnt", nullable = false)
    @ColumnDefault("0")
    var cnt: Int

): BaseEntity()




