package io.github.abdulroufsidhu.ambaar.employee

import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeQueries: JpaRepository<Employee, String> {
}