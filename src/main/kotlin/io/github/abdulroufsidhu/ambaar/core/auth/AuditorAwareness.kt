package io.github.abdulroufsidhu.ambaar.core.auth

import io.github.abdulroufsidhu.ambaar.user.data_models.User
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.Optional
import java.util.UUID


@Component
class AuditorAwareness : AuditorAware<UUID> {
    override fun getCurrentAuditor(): Optional<UUID> {
        val authentication: Authentication? = SecurityContextHolder.getContext().authentication

        if (authentication == null || !authentication.isAuthenticated) {
            return Optional.empty()
        }

        return (authentication.principal as User).id?.let { Optional.of(it) } ?: Optional.empty()
    }
}