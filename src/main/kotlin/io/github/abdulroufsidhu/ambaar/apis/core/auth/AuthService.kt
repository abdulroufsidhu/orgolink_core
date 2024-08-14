package io.github.abdulroufsidhu.ambaar.apis.core.auth

import io.github.abdulroufsidhu.ambaar.apis.core.auth.tokenizer.TokenService
import io.github.abdulroufsidhu.ambaar.apis.user.SecurityUserService
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.request.SignInRequest
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.request.SignInResponse
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
    fun authentication(authenticationRequest: SignInRequest): SignInResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.email,
                authenticationRequest.password
            )
        )
        val user = userDetailsService.loadUserByUsername(authenticationRequest.email)
        val accessToken = createAccessToken(user)
        return SignInResponse(accessToken, user.copy(password = ""))
    }

    private fun createAccessToken(user: UserDetails) = tokenService.generate( userDetails = user )

}