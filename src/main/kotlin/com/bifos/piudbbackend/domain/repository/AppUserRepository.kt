package com.bifos.piudbbackend.domain.repository

import com.bifos.piudbbackend.domain.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AppUserRepository : JpaRepository<AppUser, Long> {

    fun findByEmail(email: String): Optional<AppUser>

    fun findByEmailStartsWith(partialEmail: String): List<AppUser>
}