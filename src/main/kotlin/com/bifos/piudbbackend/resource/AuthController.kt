package com.bifos.piudbbackend.resource

import com.bifos.piudbbackend.resource.dto.LoginRequest
import com.bifos.piudbbackend.security.jwt.JwtTokenProvider
import com.bifos.piudbbackend.service.AppUserService
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    val appUserService: AppUserService,
    val jwtTokenProvider: JwtTokenProvider,
    val authenticationManagerBuilder: AuthenticationManagerBuilder
) {

    @PostMapping("/auth/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<*> {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        val authenticationToken = loginRequest.toAuthentication()

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        val authentication: Authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken)

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        val tokenInfo = jwtTokenProvider.generate(authentication)

        return ResponseEntity.ok(tokenInfo)
    }
}