package com.bifos.piudbbackend.service.impl

import com.bifos.piudbbackend.domain.AppUser
import com.bifos.piudbbackend.domain.Role
import com.bifos.piudbbackend.domain.repository.AppUserRepository
import com.bifos.piudbbackend.domain.repository.RoleRepository
import com.bifos.piudbbackend.service.AppUserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class DefaultAppUserService(
    private val appUserRepository: AppUserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) : AppUserService {

    @Transactional(readOnly = true)
    override fun getUser(id: Long): AppUser {
        return appUserRepository.findById(id).orElseThrow { EntityNotFoundException("Invalid userId") }
    }

    @Transactional(readOnly = true)
    override fun findUserByEmail(email: String): AppUser {
        return appUserRepository.findByEmail(email).orElseThrow { EntityNotFoundException("Invalid email") }
    }

    @Transactional(readOnly = true)
    override fun findUsersByEmail(partialEmail: String): List<AppUser> {
        return appUserRepository.findByEmailStartsWith(partialEmail)
    }

    @Transactional(readOnly = false)
    override fun createUser(user: AppUser): Long {
        user.apply {
            password = passwordEncoder.encode(password)
        }

        val roles = hashSetOf<Role>()
        roles.add(roleRepository.findById(1).orElseThrow { EntityNotFoundException("Invalid role") })
        user.roles = roles

        val newUser = appUserRepository.save(user)
        return newUser.id
    }
}