package io.github.abdulroufsidhu.ambaar.core

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.SpecVersion
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun publicApi() = GroupedOpenApi.builder().group("public-apis").pathsToMatch("/**").build()

    @Bean
    fun customOpenApi() =
        OpenAPI().info(Info().title("API").version("3.0"))
            .components(
                Components()
                    .addSecuritySchemes(
                    "bearerAuth",
                    SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                        .bearerFormat("JWT")
                )
            )
            .security(
                listOf(SecurityRequirement().addList("bearerAuth"))
            )

}