package com.bifos.piudbbackend.security

import com.bifos.piudbbackend.domain.repository.AppUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.security.InvalidParameterException
import javax.persistence.EntityNotFoundException

@Service
class AppUserDetailsService(
    val appUserRepository: AppUserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw InvalidParameterException("username은 null이 될 수 없음")
        }

        return appUserRepository.findByEmail(username)
            .orElseThrow { EntityNotFoundException("Invalid username/password.") }
    }
}