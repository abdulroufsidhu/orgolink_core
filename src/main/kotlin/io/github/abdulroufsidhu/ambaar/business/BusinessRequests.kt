package io.github.abdulroufsidhu.ambaar.business

import io.github.abdulroufsidhu.ambaar.branch.Branch
import io.github.abdulroufsidhu.ambaar.core.Responser
import io.github.abdulroufsidhu.ambaar.employee.Employee
import io.github.abdulroufsidhu.ambaar.employee.EmployeeLogic
import io.github.abdulroufsidhu.ambaar.user.data_models.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
    fun createBusiness(
        @AuthenticationPrincipal user: User,
        @RequestBody branch: Branch,
    ) = Responser.success {
        employeeLogic.create(
            Employee(
                permissions = Employee.Permissions.entries,
                user = user,
                branch = branch,
                designation = "Founder",
                active = true,
            )
        )
    }


    @PatchMapping("")
    private fun updateBusiness(@RequestBody business: Business) = Responser.success {
        businessLogic.update(business)
    }

    @DeleteMapping("/{id}")
    private fun deleteBusiness(@PathVariable("id") id: String) =
        Responser.success {
            businessLogic.delete(id)
        }
}