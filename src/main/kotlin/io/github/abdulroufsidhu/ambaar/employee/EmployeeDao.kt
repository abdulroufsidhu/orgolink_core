package io.github.abdulroufsidhu.ambaar.employee

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface EmployeeDao: JpaRepository<Employee, UUID> {
    fun findByBranchId(branchId: UUID): Optional<List<Employee>>

    fun findByUserId(userId: UUID): Optional<List<Employee>>

    fun findByActive(active: Boolean): Optional<List<Employee>>

}