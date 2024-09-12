package io.github.abdulroufsidhu.orgolink.core.employee

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional
import java.util.UUID

interface EmployeeDao: JpaRepository<io.github.abdulroufsidhu.orgolink.core.employee.Employee, UUID> {

    @Query("SELECT e FROM Employee e WHERE e.id = :id")
    @EntityGraph(attributePaths = ["user", "branch", "permissions"])
    fun findByEmployeeId(id: UUID) : Optional<io.github.abdulroufsidhu.orgolink.core.employee.Employee>
    fun findByBranchId(branchId: UUID): Optional<List<io.github.abdulroufsidhu.orgolink.core.employee.Employee>>

    fun findByUserId(userId: UUID): Optional<List<io.github.abdulroufsidhu.orgolink.core.employee.Employee>>

    fun findByActive(active: Boolean): Optional<List<io.github.abdulroufsidhu.orgolink.core.employee.Employee>>

}