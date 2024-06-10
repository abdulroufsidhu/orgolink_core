package io.github.abdulroufsidhu.ambaar.employee

import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeDao: JpaRepository<Employee, String> {
}