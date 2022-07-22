package com.bifos.piudbbackend.domain.repository

import com.bifos.piudbbackend.domain.PiuVersion
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PiuVersionRepository : JpaRepository<PiuVersion, Long> {

    fun findByName(name: String) : Optional<PiuVersion>
}