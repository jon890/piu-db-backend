package com.bifos.piudbbackend.web

import com.bifos.piudbbackend.service.impl.SongService
import com.bifos.piudbbackend.web.dto.PiuVersionRequest
import com.bifos.piudbbackend.web.dto.Router
import com.bifos.piudbbackend.web.dto.SongRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Router.SONG_API_URL)
class SongController(
    private val songService: SongService
) {

    @PostMapping
    fun addSong(@RequestBody dto: SongRequest.Add) {

    }

    @PostMapping("/versions")
    fun addVersion(@RequestBody dto: PiuVersionRequest.Add): ResponseEntity<*> {
        val result = songService.addVersion(dto.toEntity())
        return ResponseEntity.ok(result)
    }
}