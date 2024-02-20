package org.alram.lh.rental.utils

import java.lang.IllegalArgumentException

enum class LhApiParameters(val code: String, val description: String) {
        광주("29","광주"),
        서울("11","서울"),
        부산("26","부산"),
        대구("27","대구"),
        인천("28","인천"),
        대전("30","대전"),
        울산("31","울산"),
        세종("36110","세종"),
        경기도("41","경기도"),
        강원도("42","강원도"),
        충북("43","충북"),
        충남("44","충남"),
        전북("45","전북"),
        전남("46","전남"),
        경북("47","경북"),
        경남("48","경남"),
        제주도("50","제주도"),


        토지("01","토지"),
        분양주택("05","분양주택"),
        임대주택("06","임대주택"),
        주거복지("13","주거복지"),
        장가("22","장가"),
        신혼희망타운("39","신혼희망타운");




        companion object {
                private val cityCodeList : List<String>  = listOf(  광주.code,
                                                                서울.code,
                                                                부산.code,
                                                                대구.code,
                                                                인천.code,
                                                                대전.code,
                                                                울산.code,
                                                                세종.code,
                                                                경기도.code,
                                                                강원도.code,
                                                                충북.code,
                                                                충남.code,
                                                                전북.code,
                                                                전남.code,
                                                                경북.code,
                                                                경남.code,
                                                                제주도.code
                )

                fun getCityList(): List<String> =
                    cityCodeList

            fun of(description: String): String{
                for (elem in LhApiParameters.values()){
                    if(elem.name == description){
                        return elem.code
                    }
                }
                throw NoSuchElementException("일치하는 검색 유형코드가 없습니다.")
            }
        }
}