package org.alram.lh.rental.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiResponse(
    @JsonProperty("PAN_NT_ST_DT")//PAN_NT_ST_DT
   val startDT: String = "" ,
    @JsonProperty("AIS_TP_CD_NM")
    val place: String = "" ,
    @JsonProperty("CNP_CD_NM")
    val city: String = "" ,
    @JsonProperty("PAN_DT")
    val noticeStartDT: String = "" ,
    @JsonProperty("DTL_URL")
    val noticeUrl: String = "" ,
    @JsonProperty("PAN_NM")
    val title: String = "" ,
    @JsonProperty("UPP_AIS_TP_NM")
    val kind: String = ""
)