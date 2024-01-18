
package org.alram.lh.rental.service
import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

private val logger = KotlinLogging.logger {}
@Service
@RequiredArgsConstructor
class LhOpenApiServiceImpl(
     private val restTemplate: RestTemplate
) {
     val panSS: String = URLEncoder
          .encode("공고중", StandardCharsets.UTF_8.toString())
     /**
      *
      * http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1?serviceKey=OPrzGNbeGKWMffAEpVmOkqzrC9FjbuvyHR%2F3DWvQFtl%2BrPp0mmSv8nR6y8j6pnEr3S6KQltHw%2FuSCU%2BevaeyKA%3D%3D
      * &PG_SZ=10
      * &PAGE=1
      * &PAN_SS=공고중
      * &CNP_CD=29
      * &UPP_AIS_TP_CD=06
      * */
     fun searchHouse(baseUrl: String, encodeKey: String, cityCode: String, kindOfHouse: String): ResponseEntity<String> {
          var uri = UriComponentsBuilder
               .fromHttpUrl(baseUrl)
               .queryParam("serviceKey", encodeKey)
               .queryParam("PG_SZ", "10")
               .queryParam("PAGE", "1")
               .queryParam("PAN_SS", panSS)
               .queryParam("CNP_CD", cityCode)
               .queryParam("UPP_AIS_TP_CD",kindOfHouse)
               .build(true)


          return restTemplate.getForEntity(uri.toUri(), String::class.java)
     }


}





















































































































































































































