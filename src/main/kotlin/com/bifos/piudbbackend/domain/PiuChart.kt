package com.bifos.piudbbackend.domain

import javax.persistence.*

@Entity
@Table(name = "piu_charts")
class PiuChart(

    @Enumerated(EnumType.STRING)
    val type: PiuChartType,

    val level: Int

) : BaseEntity() {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    lateinit var song: Song

}