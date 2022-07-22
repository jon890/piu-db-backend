package com.bifos.piudbbackend.web.dto

import com.bifos.piudbbackend.domain.AppUser

class SignInRequest(
    val email: String,

    val password: String,

    val piuNickname: String
) {
    fun toEntity() : AppUser {
        return AppUser(
            email = email,
            password = password,
            piuNickname = piuNickname
        )
    }
}