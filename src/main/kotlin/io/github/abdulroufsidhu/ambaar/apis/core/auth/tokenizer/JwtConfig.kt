package io.github.abdulroufsidhu.ambaar.apis.core.auth.tokenizer

import io.github.abdulroufsidhu.ambaar.apis.core.caching.HibernateInitializer
import io.github.abdulroufsidhu.ambaar.apis.user.SecurityUserService
import io.github.abdulroufsidhu.ambaar.apis.user.UserDao
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@ConfigurationProperties("jwt")
data class JwtProperties(
    val key: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long,
)

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class JwtConfig {
    @Bean
    fun userDetailsService(userRepository: UserDao, hibernateInitializer: HibernateInitializer): UserDetailsService =
        SecurityUserService(userRepository, hibernateInitializer)

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(
        userRepository: UserDao,
        hibernateInitializer: HibernateInitializer
    ): AuthenticationProvider =
        DaoAuthenticationProvider().also {
            it.setUserDetailsService(userDetailsService(userRepository, hibernateInitializer))
            it.setPasswordEncoder(encoder())
        }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager
}
