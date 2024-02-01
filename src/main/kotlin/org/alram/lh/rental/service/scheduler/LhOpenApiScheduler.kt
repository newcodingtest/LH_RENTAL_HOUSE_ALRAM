package org.alram.lh.rental.service.scheduler

import mu.KotlinLogging
import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.service.port.LhBatchRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class LhOpenApiScheduler(
    private val lhOpenApiServiceImpl: LhOpenApiCall,
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

        logger.info { "request ${value}"}
        val notice = LhNotice(code = 2906,
            content = value)

        logger.info { "notice ${notice}" }
        notices.add(notice)

        lhBatchRepository.save(notices)

    }
}