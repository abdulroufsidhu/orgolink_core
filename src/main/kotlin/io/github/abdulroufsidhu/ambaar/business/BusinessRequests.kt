package io.github.abdulroufsidhu.ambaar.business

import io.github.abdulroufsidhu.ambaar.core.Responser
import io.github.abdulroufsidhu.ambaar.employee.Employee
import io.github.abdulroufsidhu.ambaar.employee.EmployeeLogic
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/business")
@CrossOrigin
class BusinessRequests(
    private val businessLogic: BusinessLogic,
    private val employeeLogic: EmployeeLogic,
) {
    @PutMapping("")
    fun createBusiness(@RequestBody employee: Employee) = Responser.success {
        employeeLogic.create(employee.copy(permissions = Employee.Permissions.entries.toList(), active = true))
    }

    @PatchMapping("")
    fun updateBusiness(@RequestBody business: Business) = Responser.success {
        businessLogic.update(business)
    }

    @DeleteMapping("/{id}")
    fun deleteBusiness(@PathVariable("id") id: String) =
        Responser.success {
            businessLogic.delete(id)
        }
}