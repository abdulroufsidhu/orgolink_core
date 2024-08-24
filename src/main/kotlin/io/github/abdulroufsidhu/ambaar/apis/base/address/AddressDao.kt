package io.github.abdulroufsidhu.ambaar.apis.base.address

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.jdbc.core.JdbcTemplate
import java.util.Optional
import java.util.UUID

interface AddressDao : JpaRepository<Address, UUID> {

    fun findByStateAndCityAndStreetAndZip(
        state: String,
        city: String,
        street: String,
        zip: String
    ): Optional<Set<Address>>

    fun findByCityLikeAndStateLikeAndCountry(
        city: String?,
        state: String?,
        country: Address.Country
    ): Set<Address>


}