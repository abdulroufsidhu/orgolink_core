package io.github.abdulroufsidhu.ambaar.address

import org.springframework.data.jpa.repository.JpaRepository
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