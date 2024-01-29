package org.alram.lh.rental.infra.entity

import jakarta.persistence.*
import org.alram.lh.rental.domain.LhNotice

@Entity
@Table(name = "space_conversations")
class LhNoticeEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "content")
    var content: String? = null
){
    fun fromModel(lhNotice: LhNotice): LhNoticeEntity {
        return LhNoticeEntity(lhNotice.id, lhNotice.content)
    }

    fun toModel(): LhNotice {
        return LhNotice(id, content)
    }
}




