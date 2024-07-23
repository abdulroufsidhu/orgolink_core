package io.github.abdulroufsidhu.ambaar.apis.core.auth

import io.github.abdulroufsidhu.ambaar.apis.core.auth.tokenizer.TokenService
import io.github.abdulroufsidhu.ambaar.apis.user.SecurityUserService
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.SignInRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: SecurityUserService,
    private val tokenService: TokenService,
) {
    fun authentication(authenticationRequest: SignInRequest): String {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.email,
                authenticationRequest.password
            )
        )
        val user = userDetailsService.loadUserByUsername(authenticationRequest.email)
        val accessToken = createAccessToken(user)
        return accessToken
    }

    private fun createAccessToken(user: UserDetails) = tokenService.generate(
        userDetails = user
    )

}