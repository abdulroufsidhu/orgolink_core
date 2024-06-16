package io.github.abdulroufsidhu.ambaar.branch

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface BranchDao: JpaRepository<Branch, String> {

    fun findByEmail(email: String): Optional<Branch>

    fun findByPhone(phone: String): Optional<Branch>

    fun findByBusinessId(businessId: String): Optional<List<Branch>>

}