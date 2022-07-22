package com.bifos.piudbbackend.service.impl

import com.bifos.piudbbackend.domain.PiuVersion
import com.bifos.piudbbackend.domain.Song
import com.bifos.piudbbackend.domain.repository.PiuChartRepository
import com.bifos.piudbbackend.domain.repository.PiuVersionRepository
import com.bifos.piudbbackend.domain.repository.SongRepository
import com.bifos.piudbbackend.web.dto.SongRequest
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

class SongServiceImpl(
    private val songRepository: SongRepository,
    private val piuVersionRepository: PiuVersionRepository,
    private val piuChartRepository: PiuChartRepository
) : SongService {

    @Transactional
    override fun addWithCharts(dto: SongRequest.Add): Song {
        val version =
            piuVersionRepository.findByName(dto.versionName).orElseThrow { EntityNotFoundException("버전이 존재하지 않습니다") }

        val song = songRepository.save(dto.toEntity(version))
        val charts = dto.toChartsEntity(song)
        piuChartRepository.saveAll(charts)

        return song
    }

    @Transactional
    override fun addVersion(piuVersion: PiuVersion): PiuVersion {
        return piuVersionRepository.save(piuVersion)
    }
}