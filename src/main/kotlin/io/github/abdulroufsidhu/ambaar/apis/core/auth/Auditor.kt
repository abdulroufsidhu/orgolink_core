package io.github.abdulroufsidhu.ambaar.apis.core.auth

import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.Optional
import java.util.UUID
import java.util.logging.Logger


@Component
class Auditor : AuditorAware<UUID> {
    private val logger = Logger.getLogger(this::class.java.name)
    override fun getCurrentAuditor(): Optional<UUID> {
        val authentication: Authentication? = SecurityContextHolder.getContext().authentication
        if (authentication == null || !authentication.isAuthenticated || authentication.principal == "anonymousUser") {
            return Optional.ofNullable(null)
        }
        return Optional.ofNullable((authentication.principal as User).id)
    }
}