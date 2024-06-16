package io.github.abdulroufsidhu.ambaar.employee

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employee")
class EmployeeRequests(
    private val employeeLogic: EmployeeLogic
) {

    @GetMapping("")
    fun getEmployees(
        @RequestParam("branch_id") branchId: String,
        @RequestParam("user_id") userId: String
    ): List<Employee> {
        return employeeLogic.read(branchId, userId)
    }

    @PutMapping("")
    fun createEmployee(
        @RequestBody employee: Employee
    ) = employeeLogic.create(employee)

    @PatchMapping("")
    fun updateEmployee(
        @RequestBody employee: Employee
    ) = employeeLogic.update(employee)

    @DeleteMapping("/{id}")
    fun deleteEmployee(
        @PathVariable("id") employeeId: String
    ) = employeeLogic.delete(employeeId)


}