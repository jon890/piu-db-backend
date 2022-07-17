package com.bifos.piudbbackend.repository

import com.bifos.piudbbackend.domain.AppUser
import org.springframework.stereotype.Repository

@Repository
class AppUserRepository {

    private val users = mutableListOf<AppUser>()

    fun getUsers() : List<AppUser> {
        return users
    }

    fun findUserByEmail(email: String) : AppUser? {
        return users.find { it.email == email }
    }

    fun createUser(user: AppUser) : Int {
        users.add(user)
        return 1
    }
}