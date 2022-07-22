package com.bifos.piudbbackend.domain.repository

import com.bifos.piudbbackend.domain.Song
import org.springframework.data.jpa.repository.JpaRepository

interface SongRepository : JpaRepository<Song, Long> {
}