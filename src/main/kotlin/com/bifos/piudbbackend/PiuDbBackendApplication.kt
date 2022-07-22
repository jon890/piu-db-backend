package com.bifos.piudbbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class PiuDbBackendApplication

fun main(args: Array<String>) {
	runApplication<PiuDbBackendApplication>(*args)
}