package com.bifos.piudbbackend.domain.repository

import com.bifos.piudbbackend.domain.PiuChart
import org.springframework.data.jpa.repository.JpaRepository

interface PiuChartRepository : JpaRepository<PiuChart, Long> {
}