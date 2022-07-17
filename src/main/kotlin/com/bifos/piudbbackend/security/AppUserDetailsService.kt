package com.bifos.piudbbackend.security

import com.bifos.piudbbackend.domain.repository.AppUserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.security.InvalidParameterException

@Service
class AppUserDetailsService(
    val appUserRepository: AppUserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw InvalidParameterException("username은 null이 될 수 없음")
        }

        val user = appUserRepository.findByEmail(username)
            .orElseThrow { UsernameNotFoundException("Invalid username/password") }
        val grantedAuthorities = user.roles.map { SimpleGrantedAuthority(it.name) }.toSet()
        return User(user.email, user.password, grantedAuthorities)
    }
}