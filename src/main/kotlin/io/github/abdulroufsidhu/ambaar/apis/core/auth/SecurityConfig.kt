package io.github.abdulroufsidhu.ambaar.apis.core.auth

import io.github.abdulroufsidhu.ambaar.apis.core.auth.tokenizer.JwtAuthFilter
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User
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
        employeeFilter: EmployeeFilter,
    ): SecurityFilterChain {
        http
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests { customizer ->
                customizer
                    .requestMatchers(
                        "*/**",
                        "/api/users/create",
                        "/api/docs/**",
                        "/api/users/sign-in",
                    ).permitAll()
                    .requestMatchers("/api/**").hasAuthority(User.UserAuthorities.USER.name)

            }.sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.authenticationProvider(authenticationProvider).addFilterBefore(
                jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java
            ).addFilterAfter(employeeFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

}