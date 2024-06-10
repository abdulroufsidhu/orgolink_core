package io.github.abdulroufsidhu.ambaar.address

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface AddressDao : JpaRepository<Address, String> {
    fun findByStateAndCityAndStreetAndZip(
        state: String,
        city: String,
        street: String,
        zip: String
    ): Optional<Address>
}