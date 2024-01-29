package org.alram.lh.rental.service

import lombok.RequiredArgsConstructor
import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.service.port.LhBatchRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.Arrays

@Component
@RequiredArgsConstructor
class LhOpenApiScheduler(
    private val lhOpenApiServiceImpl: LhOpenApiServiceImpl,
    private val lhBatchRepository: LhBatchRepository
    ) {
    private val notices: ArrayList<LhNotice> = ArrayList()

    //db batch insert
    @Scheduled(cron = "\${openApi.call.cron}")
    private fun create(){
        notices.clear()

        val value  = lhOpenApiServiceImpl.searchHouse("http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1",
            "OPrzGNbeGKWMffAEpVmOkqzrC9FjbuvyHR%2F3DWvQFtl%2BrPp0mmSv8nR6y8j6pnEr3S6KQltHw%2FuSCU%2BevaeyKA%3D%3D",
            "29",
            "06").body?:""

        val notice = LhNotice(2906,
                                value)

        notices.add(notice)

        lhBatchRepository.save(notices)

    }
}