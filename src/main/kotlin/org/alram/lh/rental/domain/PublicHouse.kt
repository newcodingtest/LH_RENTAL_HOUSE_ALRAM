package org.alram.lh.rental.domain

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class PublicHouse(
    /**
     * 지역
     * */
    private val city: String,
    /**
     * 임대 유형
     * */
    private val kind: String,
    /**
     * 제목
     * */
    private val title: String,
    /**
     * 공고시작일
     * */
    private val staDT: String,
    /**
     * 공고종료일
     * */
    private val endDT: String,
    /**
     * 상세주소
     * */
    private var url: String

    )