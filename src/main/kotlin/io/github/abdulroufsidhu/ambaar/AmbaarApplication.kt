package io.github.abdulroufsidhu.ambaar

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@ComponentScan(basePackages = ["io.github.abdulroufsidhu.ambaar"])
@EnableJpaAuditing
@EnableAutoConfiguration
@SpringBootApplication
@OpenAPIDefinition(
    info = io.swagger.v3.oas.annotations.info.Info(
        title = "Ambaar API",
        version = "1.0"
    )
)
class AmbaarApplication

fun main(args: Array<String>) {
    runApplication<AmbaarApplication>(*args)
}
