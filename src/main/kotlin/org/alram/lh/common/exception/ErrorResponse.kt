package org.alram.lh.common.exception

import lombok.Getter

@Getter
class ErrorResponse {
    val reason: String
    val code: Int

    constructor(reason: String, code: Int) {
        this.reason = reason
        this.code = code
    }
}