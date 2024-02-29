package org.alram.lh.rental.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty



/**
 * redis에서 cache로 가져올때 json to object 역직렬화를 위해서 @JsonCreator, @JsonProperty 사용
 * 코틀린의 기본 생성자가 존재 하지만 컴파일러가 생성하기 때문에 jackson같은 외부 라이브러리 사용시에는 문제가 될수 있음
 * 때문에 명시적으로  @JsonCreator, @JsonProperty 선언해야 외부 라이브러리에서 알아먹는다.
 * */

data class LhNotice @JsonCreator constructor (
     @JsonProperty("code")
     val code: Long,
     @JsonProperty("content")
     val content: String,
     @JsonProperty("cnt")
     val cnt: Int,
     @JsonProperty("city")
     val city: String
)