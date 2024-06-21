package io.github.abdulroufsidhu.ambaar.employee

import io.github.abdulroufsidhu.ambaar.core.Responser
import org.springframework.web.bind.annotation.CrossOrigin
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
@CrossOrigin
class EmployeeRequests(
    private val employeeLogic: EmployeeLogic
) {

    @GetMapping("")
    fun getEmployees(
        @RequestParam("branch_id") branchId: String,
        @RequestParam("user_id") userId: String
    ) = Responser.success {
        employeeLogic.read(branchId, userId)
    }

    @PutMapping("")
    fun createEmployee(
        @RequestBody employee: Employee
    ) = Responser.success {
        employeeLogic.create(employee)
    }

    @PatchMapping("")
    fun updateEmployee(
        @RequestBody employee: Employee
    ) = Responser.success {
        employeeLogic.update(employee)
    }

    @DeleteMapping("/{id}")
    fun deleteEmployee(
        @PathVariable("id") employeeId: String
    ) = Responser.success {
        employeeLogic.delete(employeeId)
    }


}