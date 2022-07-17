package com.bifos.piudbbackend.service.impl

import com.bifos.piudbbackend.domain.AppUser
import com.bifos.piudbbackend.repository.AppUserRepository
import com.bifos.piudbbackend.service.AppUserService
import org.springframework.stereotype.Service

@Service
class DefaultAppUserService(private val appUserRepository: AppUserRepository) : AppUserService {

    override fun getUser(id: Long): AppUser {
        TODO("Not yet implemented")
    }

    override fun findUserByEmail(email: String): AppUser {
        TODO("Not yet implemented")
    }

    override fun findUsersByEmail(partialEmail: String): List<AppUser> {
        TODO("Not yet implemented")
    }

    override fun createUser(user: AppUser): Int {
        return appUserRepository.createUser(user)
    }
}