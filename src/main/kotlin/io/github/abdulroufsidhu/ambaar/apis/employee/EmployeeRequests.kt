package io.github.abdulroufsidhu.ambaar.apis.employee

import io.github.abdulroufsidhu.ambaar.apis.core.Responser
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User
import io.swagger.v3.oas.annotations.parameters.RequestBody
import jakarta.transaction.Transactional
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/employee")
@CrossOrigin
class EmployeeRequests(
    private val employeeLogic: EmployeeLogic
) {

    @GetMapping("")
    fun getEmployees(
        @RequestParam("branch_id") branchId: String?,
        @RequestParam("user_id") userId: String?,
        @AuthenticationPrincipal user: User,
        @RequestAttribute("employee") employee: Employee?,
    ) = Responser.success {
        if (branchId.isNullOrEmpty() && userId.isNullOrEmpty()) return@success user.id?.let {
            employeeLogic.read( it )
        }
        assert(employee?.permissions?.contains(Employee.Permissions.EMPLOYEE_READ_ALL) == true)
        employeeLogic.read(branchId, userId)
    }

    @PutMapping("")
    fun createEmployee(
        @RequestAttribute("employee") emp: Employee,
        @RequestBody employee: Employee
    ) = Responser.success {
        assert(emp.permissions.contains(Employee.Permissions.EMPLOYEE_CREATE))
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