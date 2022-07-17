package com.bifos.piudbbackend.security

import com.bifos.piudbbackend.domain.AppUser
import com.bifos.piudbbackend.repository.AppUserRepository
import org.springframework.security.core.GrantedAuthority
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

        val user = appUserRepository.findUserByEmail(username)
        if (user == null) {
            throw UsernameNotFoundException("Invalid username/password")
        }

        return AppUserDetails(user)
    }

    private inner class AppUserDetails(
        user: AppUser
    ) : AppUser(user.id, user.email, user.password, user.piuNickname), UserDetails {
        override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
            return AppUserAuthorityUtils.createAuthorities(this)
        }

        override fun getPassword(): String {
            return password
        }

        override fun getUsername(): String {
           return email
        }

        override fun isAccountNonExpired(): Boolean {
            return true
        }

        override fun isAccountNonLocked(): Boolean {
            return true
        }

        override fun isCredentialsNonExpired(): Boolean {
            return true
        }

        override fun isEnabled(): Boolean {
            return true
        }
    }
}