package io.github.abdulroufsidhu.ambaar.core

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun publicApi() = GroupedOpenApi.builder().group("public-apis").pathsToMatch("/**").build()

    @Bean
    fun customOpenApi() = OpenAPI().info(Info().title("API").version("1.0")).components(Components())

}