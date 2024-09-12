package io.github.abdulroufsidhu.orgolink.core.branch

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface BranchDao: JpaRepository<io.github.abdulroufsidhu.orgolink.core.branch.Branch, UUID> {

    fun findByEmail(email: String): Optional<io.github.abdulroufsidhu.orgolink.core.branch.Branch>

    fun findByPhone(phone: String): Optional<io.github.abdulroufsidhu.orgolink.core.branch.Branch>

    fun findByBusinessId(businessId: UUID): Optional<List<io.github.abdulroufsidhu.orgolink.core.branch.Branch>>

}