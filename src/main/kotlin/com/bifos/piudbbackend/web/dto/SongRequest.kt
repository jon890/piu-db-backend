package com.bifos.piudbbackend.web.dto

import com.bifos.piudbbackend.domain.PiuChart
import com.bifos.piudbbackend.domain.PiuVersion
import com.bifos.piudbbackend.domain.Song

class SongRequest {

    inner class Add(
        val name: String,
        val versionName: String,
        val charts: List<PiuChartRequest.Add>
    ) {
        fun toEntity(piuVersion: PiuVersion): Song {
            val song = Song(name)
            song.version = piuVersion
            return song
        }

        fun toChartsEntity(song: Song): List<PiuChart> {
            return charts.map {
                it.toEntity(song)
            }
        }
    }
}