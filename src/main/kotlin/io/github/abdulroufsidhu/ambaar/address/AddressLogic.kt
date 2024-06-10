package io.github.abdulroufsidhu.ambaar.address

import org.springframework.stereotype.Service
import java.util.Optional

@Service
class AddressLogic(
    private val addressDao: AddressDao
) {


    @Throws(IllegalArgumentException::class, NoSuchElementException::class)
    fun saveOrUpdate(address: Address) = try {
        addressDao.save(address)
    } catch (e: Exception) {
        if (address.state == null) throw IllegalArgumentException("State is required")
        if (address.city == null) throw IllegalArgumentException("City is required")
        if (address.street == null) throw IllegalArgumentException("Street is required")
        if (address.zip == null) throw IllegalArgumentException("Zip is required")
        addressDao.findByStateAndCityAndStreetAndZip(
            address.state!!,
            address.city!!,
            address.street!!,
            address.zip!!,
        ).orElseThrow()
    }

    @Throws(IllegalArgumentException::class, NoSuchElementException::class)
    fun find(address: Address): Optional<Address> {
        if (address.id.isNullOrBlank().not()) return addressDao.findById(address.id!!)

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