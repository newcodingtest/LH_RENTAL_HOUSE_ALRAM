package org.alram.lh.rental.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiResponse(
   /**
    * 공고게시일
    * */
    @JsonProperty("PAN_NT_ST_DT")//PAN_NT_ST_DT
    val postedDate: String = "" ,
    @JsonProperty("AIS_TP_CD_NM")
    val majorCategiry: String = "" ,
    @JsonProperty("CNP_CD_NM")
    val city: String = "" ,
   /**
    * 모집공고 시작일
    * */
    @JsonProperty("PAN_DT")
    val recruitStartDT: String = "" ,
   /**
    * 모집공고 종료일
    * */
    @JsonProperty("CLSG_DT")
    val recruitEndDT: String = "" ,
    @JsonProperty("DTL_URL")
    val noticeUrl: String = "" ,
    @JsonProperty("PAN_NM")
    val title: String = "" ,
    @JsonProperty("UPP_AIS_TP_NM")
    val subCategiry: String = ""
)