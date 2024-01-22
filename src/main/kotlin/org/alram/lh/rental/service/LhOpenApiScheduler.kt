package org.alram.lh.rental.service

import lombok.RequiredArgsConstructor
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
@RequiredArgsConstructor
class LhOpenApiScheduler(
    private val lhOpenApiServiceImpl: LhOpenApiServiceImpl
) {


    //db batch insert
    @Scheduled(cron = "\${openApi.call.cron}")
    private fun create(value: String){
        val value  = lhOpenApiServiceImpl.searchHouse("http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1",
            "OPrzGNbeGKWMffAEpVmOkqzrC9FjbuvyHR%2F3DWvQFtl%2BrPp0mmSv8nR6y8j6pnEr3S6KQltHw%2FuSCU%2BevaeyKA%3D%3D",
            "29",
            "06").body?:""

        /**
         * todo: db batch insert 로직 구현
         *
         * */

    }
}