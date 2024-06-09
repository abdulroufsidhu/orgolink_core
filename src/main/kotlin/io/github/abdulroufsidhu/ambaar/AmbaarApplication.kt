package io.github.abdulroufsidhu.ambaar

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableAutoConfiguration
@SpringBootApplication
class AmbaarApplication

fun main(args: Array<String>) {
	runApplication<AmbaarApplication>(*args)
}
