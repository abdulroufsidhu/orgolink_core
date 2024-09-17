package io.github.abdulroufsidhu.orgolink.core.address

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface AddressDao : JpaRepository<Address, UUID> {}
