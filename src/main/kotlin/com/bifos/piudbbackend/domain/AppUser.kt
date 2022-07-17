package com.bifos.piudbbackend.domain

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "app_users")
class AppUser(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    val email: String,

    @get:JvmName("getPassword1")
    var password: String,

    val piuNickname: String,
) : Serializable {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles = mutableSetOf<Role>()

    fun updatePassword(newPassword: String) {
        this.password = newPassword
    }
}