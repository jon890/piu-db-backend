package com.bifos.piudbbackend.service.impl

import com.bifos.piudbbackend.domain.PiuVersion
import com.bifos.piudbbackend.domain.Song
import com.bifos.piudbbackend.web.dto.SongRequest

interface SongService {

    fun addWithCharts(dto: SongRequest.Add): Song

    fun addVersion(piuVersion: PiuVersion): PiuVersion
}