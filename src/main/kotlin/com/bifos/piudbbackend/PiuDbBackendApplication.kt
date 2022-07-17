package com.bifos.piudbbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PiuDbBackendApplication

fun main(args: Array<String>) {
	runApplication<PiuDbBackendApplication>(*args)
}
