package io.github.abdulroufsidhu.ambaar.address

import org.springframework.data.jpa.repository.JpaRepository

interface AddressQueries: JpaRepository<Address, String> {
}