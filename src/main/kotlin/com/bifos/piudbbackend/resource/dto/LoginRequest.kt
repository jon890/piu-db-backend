package com.bifos.piudbbackend.resource.dto

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class LoginRequest(
    val email: String,

    val password: String,
) {

    fun toAuthentication(): UsernamePasswordAuthenticationToken {
        return UsernamePasswordAuthenticationToken(email, password)
    }
}