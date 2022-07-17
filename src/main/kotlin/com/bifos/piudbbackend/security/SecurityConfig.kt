package com.bifos.piudbbackend.security

import com.bifos.piudbbackend.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


/**
 * Security 5.7.0 버전 이후로
 * WebSecurityConfigurerAdapter 가 Deprecated 됨 => 왜 일까?
 */
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            // JWT 를 사용하기 위해 Session 설정 해제
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            // antMatchers() => o.s.s.web.util.AntPathRequestMatcher 에 의해 지원됨
            // 더 알아보려면 Apache Ant 를 참고해보자.
            .antMatchers("/auth/**").permitAll()
            .anyRequest().authenticated()

            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
