package io.github.abdulroufsidhu.ambaar.apis.address

import org.springframework.stereotype.Service
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@Service
class AddressLogic(
    private val addressDao: AddressDao
) {

    @Throws(IllegalArgumentException::class, NoSuchElementException::class)
    fun saveOrFind(address: Address): Address {
        println("address to find is: $address")
        val found = findExcludingId(address).getOrNull()?.firstOrNull()
        println("found address is: $found")
        return found ?: addressDao.save(address)
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