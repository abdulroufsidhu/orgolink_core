package io.github.abdulroufsidhu.orgolink.core

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.web.config.EnableSpringDataWebSupport

@EnableSpringDataWebSupport(
        pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO
)
@ComponentScan(basePackages = ["io.github.abdulroufsidhu.orgolink.core"])
@EnableJpaAuditing
@EnableCaching
@SpringBootApplication
@OpenAPIDefinition(
        info = io.swagger.v3.oas.annotations.info.Info(title = "Orgolink Core API", version = "1.0")
)
class OrgolinkCoreApplication

fun main(args: Array<String>) {
    runApplication<OrgolinkCoreApplication>(*args)
}
