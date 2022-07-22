package com.bifos.piudbbackend.web.dto

import com.bifos.piudbbackend.domain.PiuVersion

class PiuVersionRequest {

    inner class Add(
        val name: String
    ) {
        fun toEntity(): PiuVersion {
            return PiuVersion(name)
        }
    }
}