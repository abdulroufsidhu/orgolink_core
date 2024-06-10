package io.github.abdulroufsidhu.ambaar.business

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface BusinessDao : JpaRepository<Business, String> {
    fun findByName(name: String) : Optional<Business>
    fun findByLicence(licence: String) : Optional<Business>
}