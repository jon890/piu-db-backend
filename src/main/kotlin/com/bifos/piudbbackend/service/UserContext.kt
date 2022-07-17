package com.bifos.piudbbackend.service

import com.bifos.piudbbackend.domain.AppUser

interface UserContext {

    fun getCurrentUser(): AppUser?

    fun setCurrentUser(user: AppUser)
}