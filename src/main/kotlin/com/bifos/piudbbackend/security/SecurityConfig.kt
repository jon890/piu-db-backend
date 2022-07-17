package com.bifos.piudbbackend.security

import com.bifos.piudbbackend.domain.repository.AppUserRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain


/**
 * Security 5.7.0 버전 이후로
 * WebSecurityConfigurerAdapter 가 Deprecated 됨 => 왜 일까?
 */
@EnableWebSecurity
class SecurityConfig(private val appUserRepository: AppUserRepository) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeRequests()
            // antMatchers() => o.s.s.web.util.AntPathRequestMatcher 에 의해 지원됨
            // 더 알아보려면 Apache Ant 를 참고해보자.
            .antMatchers("/").hasAnyRole("ANONYMOUS", "USER")
            .antMatchers("/auth/login").hasAnyRole("ANONYMOUS", "USER")
            .antMatchers("/auth/logout").hasAnyRole("ANONYMOUS", "USER")
            .antMatchers("/**").hasAnyRole("USER")

            .and().formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/auth/login")
            .failureUrl("/login?error")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/")

            .and().httpBasic()
            .and().logout().logoutUrl("/auth/logout")
            .and().csrf().disable()
            .build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        return AppUserDetailsService(appUserRepository)
    }
}
