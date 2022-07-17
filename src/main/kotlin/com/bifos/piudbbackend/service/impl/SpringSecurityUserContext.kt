package com.bifos.piudbbackend.service.impl

import com.bifos.piudbbackend.domain.AppUser
import com.bifos.piudbbackend.security.AppUserAuthorityUtils
import com.bifos.piudbbackend.service.AppUserService
import com.bifos.piudbbackend.service.UserContext
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class SpringSecurityUserContext(
    val appUserService: AppUserService,
    val userDetailsService: UserDetailsService
) :
    UserContext {

    override fun getCurrentUser(): AppUser? {
        val context = SecurityContextHolder.getContext()
        val authentication = context.authentication

        // elvis operator 사용시 가독성이 좀 떨어지는 것 같은데 다른분들은 어떻게 쓸까..?
        if (authentication == null) {
            return null
        }

        return authentication.principal as AppUser
    }

    override fun setCurrentUser(user: AppUser) {
        val authorities = AppUserAuthorityUtils.createAuthorities(user)
        val authentication = UsernamePasswordAuthenticationToken(user, user.password, authorities)
        SecurityContextHolder.getContext().authentication = authentication
    }
}