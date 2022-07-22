package com.bifos.piudbbackend.domain

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "piu_versions")
class PiuVersion(

    val name: String

) : BaseEntity() {

    @OneToMany(targetEntity = Song::class, mappedBy = "version", fetch = FetchType.LAZY)
    lateinit var songs: List<Song>
}