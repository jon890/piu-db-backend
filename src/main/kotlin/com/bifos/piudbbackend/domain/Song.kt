package com.bifos.piudbbackend.domain

import javax.persistence.*

@Entity
@Table(name = "songs")
class Song(

    val name: String

) : BaseEntity() {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    lateinit var version: PiuVersion

    @OneToMany(targetEntity = PiuChart::class, mappedBy = "song", fetch = FetchType.LAZY)
    lateinit var charts: List<PiuChart>
}