package io.github.abdulroufsidhu.orgolink.core.address

import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class AddressLogic(
    private val addressDao: AddressDao,
) {

    fun insertOrReturnExisting(address: Address): UUID? =
        getAddresses(address, Pageable.ofSize(1).withPage(0)).content.firstOrNull()?.id


    fun getAddresses(address: Address, pageable: Pageable): Page<Address> =
        addressDao.findAll(Example.of(address, ExampleMatcher.matching().withIgnoreNullValues()), pageable)

    fun update(address: Address) = addressDao.save(address.apply { id = null });

}