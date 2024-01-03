package org.alram.lh.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ServerNotWorkingException::class)
    fun handleServerNotWorkingException(e: ServerNotWorkingException): ResponseEntity<ErrorResponse>{
        return ResponseEntity
            .internalServerError()
            .body(ErrorResponse("서버가 동작하지 않습니다. 다시 이용해주세요",
                                HttpStatus.INTERNAL_SERVER_ERROR.value()))
    }
}