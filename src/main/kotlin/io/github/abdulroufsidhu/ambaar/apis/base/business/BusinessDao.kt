package io.github.abdulroufsidhu.ambaar.apis.base.business

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface BusinessDao : JpaRepository<Business, UUID> {
    fun findByName(name: String) : Optional<Business>
    fun findByLicence(licence: String) : Optional<Business>
}