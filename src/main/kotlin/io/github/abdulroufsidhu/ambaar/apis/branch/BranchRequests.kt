package io.github.abdulroufsidhu.ambaar.apis.branch

import io.github.abdulroufsidhu.ambaar.apis.core.Responser
import io.github.abdulroufsidhu.ambaar.apis.employee.Employee
import io.github.abdulroufsidhu.ambaar.apis.employee.EmployeeLogic
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User
import io.swagger.v3.oas.annotations.parameters.RequestBody
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Transactional
@RestController
@RequestMapping("/api/branches")
@CrossOrigin
class BranchRequests(
    private val branchLogic: BranchLogic,
    private val employeeLogic: EmployeeLogic,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("")
    fun getBranches(
        branch: Branch?,
        @RequestAttribute("employee") employee: Employee,
    ) = Responser.success {
        val businessId = employee.branch.business?.id
            ?: throw IllegalArgumentException("branch not attached to business")

//        PermissionVerifier.verify(employee, Employee.Permissions.BRANCH_READ_ALL)

        branchLogic.get(businessId)
    }

    @PutMapping("")
    fun createBranch(
        @AuthenticationPrincipal user: User,
        @RequestBody branch: Branch,
        @RequestAttribute("employee") employee: Employee,
    ) = Responser.success {
        logger.info("${user.username} wants to create a branch with code: ${branch.code} in business: ${employee.branch.business?.name}")

//        PermissionVerifier.verify(employee, Employee.Permissions.BRANCH_CREATE)

        employeeLogic.create(
            Employee(
                branch = branch.copy(business = employee.branch.business),
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
                active = true,
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