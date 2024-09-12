package io.github.abdulroufsidhu.orgolink.core.config.auth

import io.github.abdulroufsidhu.orgolink.core.config.Responser
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler

class FailureImplementation : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        response?.apply {
            status = HttpServletResponse.SC_UNAUTHORIZED
            contentType = "application/json"
            writer.write("""${Responser.error { exception }.body}""")
            writer.flush()
        }
    }
}