package io.github.abdulroufsidhu.ambaar.apis.base.address

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service
import java.sql.ResultSet
import java.util.Optional
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Service
class AddressLogic(
    private val addressDao: AddressDao,
    private val jdbcTemplate: JdbcTemplate,
) {

    fun insertOrReturnExisting(address: Address): String? {
        val sql = """
            INSERT INTO addresses (id, house_number, street, city, state, zip, country)
            VALUES 
            ('${UUID.randomUUID()}', '${address.houseNumber}', '${address.street}', '${address.city}', '${address.state}', '${address.zip}', '${address.country?.name}')
            ON CONFLICT ON CONSTRAINT unique_address DO NOTHING
            RETURNING id;
        """.trimIndent()

        return jdbcTemplate.queryForObject(sql, String::class.java)
    }


    @Throws(IllegalArgumentException::class, NoSuchElementException::class)
    fun findIncludingId(address: Address): Optional<Set<Address>> {
        return address.id?.let { Optional.of(setOf(addressDao.getReferenceById(it))) }
            ?: findExcludingId(address)
    }

    @Throws(IllegalArgumentException::class, NoSuchElementException::class)
    fun findExcludingId(address: Address): Optional<Set<Address>> {
        if (address.state == null) throw IllegalArgumentException("State is required")
        if (address.city == null) throw IllegalArgumentException("City is required")
        if (address.street == null) throw IllegalArgumentException("Street is required")
        if (address.zip == null) throw IllegalArgumentException("Zip is required")

        return addressDao.findByStateAndCityAndStreetAndZip(
            address.state!!,
            address.city!!,
            address.street!!,
            address.zip!!,
        )
    }

}