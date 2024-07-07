package io.github.abdulroufsidhu.ambaar.branch

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface BranchDao: JpaRepository<Branch, UUID> {

    fun findByEmail(email: String): Optional<Branch>

    fun findByPhone(phone: String): Optional<Branch>

    fun findByBusinessId(businessId: UUID): Optional<List<Branch>>

}