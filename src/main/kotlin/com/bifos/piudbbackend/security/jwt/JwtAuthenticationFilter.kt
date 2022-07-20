package com.bifos.piudbbackend.security.jwt

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
) : OncePerRequestFilter() {

    companion object {
        const val HEADER_NAME = "Authorization"
        const val TOKEN_PREFIX = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val jwt = getJwt(request)

        // security config 에서 허용한 url에 대해서만 필터를 사용하지 않음
        // servlet filter를 좀더 알아봐야 할듯하다.
        // 아무튼 jwt 토큰이 없다고 예외를 던지면 모든 요청에 대해서 인증을 수행하게 되버림

        // 만약 filter 를 안타야 하는 url 인데 토큰을 보낼경우는 어떻게 처리하는가?
        jwt?.let {
            try {
                jwtTokenProvider.validate(jwt)
                val authentication = jwtTokenProvider.getAuthentication(jwt)
                SecurityContextHolder.getContext().authentication = authentication
            } catch (e : Exception) {
                // Error Handling?
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwt(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(HEADER_NAME)
        if (bearerToken != null && bearerToken.isNotEmpty() && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length)
        }

        return null
    }
}