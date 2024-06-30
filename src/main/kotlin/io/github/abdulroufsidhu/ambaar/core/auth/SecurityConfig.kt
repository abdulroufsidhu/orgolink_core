package io.github.abdulroufsidhu.ambaar.core.auth

import io.github.abdulroufsidhu.ambaar.user.data_models.User
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val authenticationProvider: AuthenticationProvider
) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthFilter,
    ): SecurityFilterChain {
        http
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests { customizer ->
                customizer
                    .requestMatchers(
                        "/users/create",
                        "/docs/**",
                        "/users/sign-in",
                    ).permitAll()
                    .requestMatchers("*/**").hasAuthority(User.UserAuthorities.USER.name)

            }.sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.authenticationProvider(authenticationProvider).addFilterBefore(
                jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java
            )
        return http.build()
    }

}