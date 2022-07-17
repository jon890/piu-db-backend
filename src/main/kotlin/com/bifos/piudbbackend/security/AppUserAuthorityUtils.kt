package com.bifos.piudbbackend.security

import com.bifos.piudbbackend.domain.AppUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

object AppUserAuthorityUtils {

    val ADMIN_ROLES = AuthorityUtils.createAuthorityList(
        "ROLE_ADMIN",
        "ROLE_USER"
    )

    val USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER")

    // TODO: ? extends GrantedAuthority to Kotlin!
    fun createAuthorities(user: AppUser): MutableCollection<out GrantedAuthority> {
        val username = user.email
        if (username.startsWith("admin")) {
            return ADMIN_ROLES
        }

        return USER_ROLES
    }
}