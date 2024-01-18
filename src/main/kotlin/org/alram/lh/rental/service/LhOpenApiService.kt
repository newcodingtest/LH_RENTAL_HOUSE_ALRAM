
package org.alram.lh.rental.service
import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.net.URL
import java.net.URLDecoder
import java.net.URLEncoder

private val logger = KotlinLogging.logger {}
@Service
@RequiredArgsConstructor
class LhOpenApiService(
     private val restTemplate: RestTemplate
) {

     /**
      *
      * http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1?serviceKey=OPrzGNbeGKWMffAEpVmOkqzrC9FjbuvyHR%2F3DWvQFtl%2BrPp0mmSv8nR6y8j6pnEr3S6KQltHw%2FuSCU%2BevaeyKA%3D%3D
      * &PG_SZ=10
      * &PAGE=1
      * &PAN_SS=공고중
      * &CNP_CD=29
      * &UPP_AIS_TP_CD=06
      * */
     fun searchHouse(url: String, cityCode: String, kindOfHouse: String): String {
          var hanguel = URLDecoder.decode("공고중","UTF-8");

          var request = url+"${hanguel}&CNP_CD=${cityCode}&UPP_AIS_TP_CD=${kindOfHouse}"


          //val request = URL(url + "&CNP_CD=%s" + "&UPP_AIS_TP_CD=%s".format(cityCode, kindOfHouse))

          logger.info { request }

          return restTemplate.getForObject(request);
     }


}





















































































































































































































