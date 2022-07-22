package com.bifos.piudbbackend.web.dto

import com.bifos.piudbbackend.domain.PiuChart
import com.bifos.piudbbackend.domain.PiuChartType
import com.bifos.piudbbackend.domain.Song

class PiuChartRequest {

    inner class Add(
        val type: PiuChartType,
        val level: Int
    ) {
        fun toEntity(song: Song): PiuChart {
            return PiuChart(type, level).apply { this.song = song }
        }
    }
}