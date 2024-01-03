package org.alram.lh.rental.exception.handler

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.DefaultResponseErrorHandler

class RestTemplateResponseHandler: DefaultResponseErrorHandler() {

    override fun handleError(response: ClientHttpResponse) {
        if (!response.statusCode.isSameCodeAs(HttpStatus.OK)){
            throw Exception()
        }
    }
}