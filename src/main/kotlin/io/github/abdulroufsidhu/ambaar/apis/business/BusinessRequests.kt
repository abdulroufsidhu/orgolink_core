package io.github.abdulroufsidhu.ambaar.apis.business

import io.github.abdulroufsidhu.ambaar.apis.branch.Branch
import io.github.abdulroufsidhu.ambaar.apis.core.Responser
import io.github.abdulroufsidhu.ambaar.apis.employee.Employee
import io.github.abdulroufsidhu.ambaar.apis.employee.EmployeeLogic
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User
import io.swagger.v3.oas.annotations.parameters.RequestBody
import jakarta.transaction.Transactional
import org.hibernate.Hibernate
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/business")
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
        if (!Hibernate.isInitialized(user)) Hibernate.initialize(user.person)
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