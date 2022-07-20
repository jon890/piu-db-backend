package com.bifos.piudbbackend.security.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}")
    private val jwtSecret: String,

    @Value("\${jwt.expiration}")
    private val jwtExpirationMs: Long,

    private val userDetailsService: UserDetailsService,
) {

    /**
     * JWT 토큰 생성
     */
    fun generate(authentication: Authentication): String {
        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationMs)
        val authorities = authentication.authorities.joinToString(separator = ",") { it.authority }

        return Jwts.builder()
            .setSubject(authentication.name)
            .claim("auth", authorities)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    /**
     * 토큰 유효성 체크
     */
    @Throws(MalformedJwtException::class,
        ExpiredJwtException::class,
        UnsupportedJwtException::class,
        IllegalArgumentException::class)
    fun validate(token: String) {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
    }

    /**
     * 토큰에서 User Id 추출
     */
    fun getUserId(token: String): String {
        val claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body

        return claims.subject
    }

    /**
     * JWT 토큰을 파싱해 스프링 시큐리티에서 사용할 인증 객체로 변환한다.
     */
    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(this.getUserId(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }
}