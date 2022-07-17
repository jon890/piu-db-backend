package com.bifos.piudbbackend.domain

import java.io.Serializable

open class AppUser(
    val id: Long,
    val email: String,

    @get:JvmName("getPassword1")
    var password: String,

    val piuNickname: String,
) : Serializable {

    fun updatePassword(newPassword: String) {
        this.password = newPassword
    }
}