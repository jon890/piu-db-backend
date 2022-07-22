package com.bifos.piudbbackend.service.impl

import com.bifos.piudbbackend.domain.PiuVersion
import com.bifos.piudbbackend.domain.Song
import com.bifos.piudbbackend.web.dto.SongRequest
import org.springframework.stereotype.Service

@Service
interface SongService {

    fun addWithCharts(dto: SongRequest.Add): Song

    fun addVersion(piuVersion: PiuVersion): PiuVersion
}