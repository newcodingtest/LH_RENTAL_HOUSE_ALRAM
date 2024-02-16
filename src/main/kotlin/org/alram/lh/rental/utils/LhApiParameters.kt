package org.alram.lh.rental.utils

enum class LhApiParameters(val code: String) {
        광주("29"),
        서울("11"),
        부산("26"),
        대구("27"),
        인천("28"),
        대전("30"),
        울산("31"),
        세종("36110"),
        경기도("41"),
        강원도("42"),
        충북("43"),
        충남("44"),
        전북("45"),
        전남("46"),
        경북("47"),
        경남("48"),
        제주도("50"),


        토지("01"),
        분양주택("05"),
        임대주택("06"),
        주거복지("13"),
        장가("22"),
        신혼희망타운("39");

        companion object {
                private val cityList : List<String>  = listOf(  광주.code,
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
                        cityList



        }
}