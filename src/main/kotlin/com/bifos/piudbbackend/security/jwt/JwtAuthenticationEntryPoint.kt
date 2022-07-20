package com.bifos.piudbbackend.security.jwt

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    /**
     * 인증 실패시 동작하는 함수
     */
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?,
    ) {
        // 유효한 자격 증명을 제공하지 않고 접근하려 할 때 401 에러
        response?.sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }
}