package org.alram.lh.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.NoSuchElementException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ServerNotWorkingException::class)
    fun handleServerNotWorkingException(e: ServerNotWorkingException): ResponseEntity<ErrorResponse>{
        return ResponseEntity
            .internalServerError()
            .body(ErrorResponse("서버가 동작하지 않습니다. 다시 이용해주세요",
                                HttpStatus.INTERNAL_SERVER_ERROR.value()))
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(e: NoSuchElementException): ResponseEntity<ErrorResponse>{
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message?:"데이터가 존재하지 않습니다.",
                HttpStatus.NOT_FOUND.value()))
    }


}