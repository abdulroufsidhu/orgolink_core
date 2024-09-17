package io.github.abdulroufsidhu.orgolink.core.employee

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface EmployeeDao : JpaRepository<Employee, UUID> {

    @Query("SELECT e FROM Employee e WHERE e.id = :id")
    @EntityGraph(attributePaths = ["user", "branch", "permissions"])
    fun findByEmployeeId(id: UUID): Optional<Employee>
    fun findByBranchId(branchId: UUID): Optional<List<Employee>>

    fun findByUserId(userId: UUID): Optional<List<Employee>>

    fun findByActive(active: Boolean): Optional<List<Employee>>

}