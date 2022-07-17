package com.bifos.piudbbackend.domain.repository

import com.bifos.piudbbackend.domain.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
}