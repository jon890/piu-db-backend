package com.bifos.piudbbackend.web.dto

import java.time.LocalDateTime

class ErrorResponse(
    val code: Int,
    val timeStamp: LocalDateTime,
    val url: String,
    val message: String,
    val developMessage: String,
) {
    companion object {
        fun of(code: Int, url: String, message: String, developMessage: String): ErrorResponse {
            return ErrorResponse(
                code = code,
                timeStamp = LocalDateTime.now(),
                url = url,
                message = message,
                developMessage = developMessage
            )
        }
    }
}