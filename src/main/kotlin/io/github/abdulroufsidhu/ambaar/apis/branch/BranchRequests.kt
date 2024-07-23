package io.github.abdulroufsidhu.ambaar.apis.branch

import io.github.abdulroufsidhu.ambaar.apis.core.Responser
import io.github.abdulroufsidhu.ambaar.apis.core.auth.PermissionVerifier
import io.github.abdulroufsidhu.ambaar.apis.employee.Employee
import io.github.abdulroufsidhu.ambaar.apis.employee.EmployeeLogic
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/branches")
@CrossOrigin
class BranchRequests(
    private val branchLogic: BranchLogic,
    private val employeeLogic: EmployeeLogic,
) {
    @GetMapping("/{employee_in_business_id}")
    fun getBranches(
        branch: Branch?,
        @PathVariable("employee_in_business_id") employeeId: String,
    ) = Responser.success {
        val employee = employeeLogic.get(employeeId)
        val businessId = employee.branch.business?.id
            ?: throw IllegalArgumentException("branch not attached to business")

        PermissionVerifier.verify(employee, Employee.Permissions.BRANCH_READ_ALL)

        branchLogic.get(businessId)
    }

    @PutMapping("/{employee_in_business_id}")
    fun createBranch(
        @AuthenticationPrincipal user: User,
        @RequestBody branch: Branch,
        @PathVariable("employee_in_business_id") employeeInBusinessId: String,
    ) = Responser.success {
        val employee = employeeLogic.get(employeeInBusinessId)
        PermissionVerifier.verify(employee, Employee.Permissions.BRANCH_CREATE)

        employeeLogic.create(
            Employee(
                branch = branch,
                designation = "Manager",
                user = user,
                permissions = listOf(
                    Employee.Permissions.BRANCH_UPDATE,
                    Employee.Permissions.INVENTORY_CREATE,
                    Employee.Permissions.INVENTORY_READ,
                    Employee.Permissions.INVENTORY_UPDATE,
                    Employee.Permissions.EMPLOYEE_CREATE,
                    Employee.Permissions.EMPLOYEE_READ_ALL,
                    Employee.Permissions.EMPLOYEE_UPDATE,
                ),
                active = true
            )
        )
    }

    @PatchMapping("")
    fun updateBranch(
        @RequestBody branch: Branch,
    ) = Responser.success {
        branchLogic.update(branch)
    }

    @DeleteMapping("/{id}")
    fun deleteBranch(
        @PathVariable("id") id: String,
    ) = Responser.success {
        branchLogic.delete(id)
    }

}