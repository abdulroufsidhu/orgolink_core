package io.github.abdulroufsidhu.ambaar.apis.core.auth.tokenizer

import io.github.abdulroufsidhu.ambaar.apis.core.Responser
import io.github.abdulroufsidhu.ambaar.apis.user.SecurityUserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val userDetailsService: SecurityUserService,
    private val tokenService: TokenService,
) : OncePerRequestFilter() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {

            val authHeader: String? = request.getHeader("Authorization")

            logger.info("authHeader: $authHeader")

            if (authHeader.doesNotContainBearerToken()) {
                filterChain.doFilter(request, response)
                return
            }
            val jwtToken = authHeader!!.extractTokenValue()
            val email = tokenService.extractEmail(jwtToken)

            if (email == null) {
                filterChain.doFilter(request, response)
                return
            }

            if (SecurityContextHolder.getContext().authentication == null) {
                val foundUser = userDetailsService.loadUserByUsername(email)

                val isTokenValid = tokenService.isValid(jwtToken, foundUser)
                logger.info("isTokenValid: $isTokenValid")

                if (isTokenValid) updateContext(foundUser, request)
            }
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            logger.error("exception: $e")
            response.apply {
                addHeader("Hx-Redirect", "/auth")
                writer.write("""${Responser.error { e }.body}""")
                writer.flush()
            }
            return
        }
    }

    private fun String?.doesNotContainBearerToken() =
        this == null || !this.startsWith("Bearer ")

    private fun String.extractTokenValue() =
        this.substringAfter("Bearer ")

    private fun updateContext(foundUser: UserDetails, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(foundUser, null, foundUser.authorities)
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authToken
    }
}