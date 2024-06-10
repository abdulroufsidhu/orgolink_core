package io.github.abdulroufsidhu.ambaar.employee

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface EmployeeDao: JpaRepository<Employee, String> {
    fun findByBranchId(branchId: String): Optional<List<Employee>>

    fun findByUserId(userId: String): Optional<Employee>

    fun findByActive(active: Boolean): Optional<List<Employee>>

}