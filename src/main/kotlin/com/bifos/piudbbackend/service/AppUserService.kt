package com.bifos.piudbbackend.service

import com.bifos.piudbbackend.domain.AppUser

interface AppUserService {

    fun getUser(id: Long): AppUser

    fun findUserByEmail(email: String): AppUser

    fun findUsersByEmail(partialEmail: String): List<AppUser>

    fun createUser(user: AppUser): Int

}