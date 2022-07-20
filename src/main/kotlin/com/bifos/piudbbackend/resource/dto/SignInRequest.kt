package com.bifos.piudbbackend.resource.dto

import com.bifos.piudbbackend.domain.AppUser
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

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