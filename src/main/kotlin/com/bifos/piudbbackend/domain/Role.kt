package com.bifos.piudbbackend.domain

import javax.persistence.*

@Entity
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    val name: String,
) {
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    val users = mutableSetOf<AppUser>()
}