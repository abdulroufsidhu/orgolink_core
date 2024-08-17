package io.github.abdulroufsidhu.ambaar.apis.sales

import io.github.abdulroufsidhu.ambaar.apis.core.Responser
import io.github.abdulroufsidhu.ambaar.apis.employee.Employee
import io.swagger.v3.oas.annotations.parameters.RequestBody
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
@RequestMapping("/api/sales")
@CrossOrigin
class SalesRequests(
    private val salesLogics: SalesLogic
) {

    @GetMapping("/{branchId}/{page}")
    fun getSales(
        @RequestAttribute("employee") employee: Employee,
        @PathVariable("branchId") branchId: String,
        @PathVariable("page") page: Int,
        @RequestParam("pageSize") size: Int,
    ) = Responser.success {
        assert(employee.permissions.contains(Employee.Permissions.VIEW_SALES))
        salesLogics.getSales(branchId, page, size)
    }

    @PutMapping("")
    fun generateSale(@RequestAttribute("employee") employee: Employee, @RequestBody sale: Sale) =
        Responser.success {
            assert(employee.permissions.contains(Employee.Permissions.GENERATE_SALES))
            salesLogics.save(sale)
        }

    @PatchMapping("")
    fun updateSale(@RequestAttribute("employee") employee: Employee, @RequestBody sale: Sale) =
        Responser.success {
            assert(employee.permissions.contains(Employee.Permissions.UPDATE_SALES))
            salesLogics.update(sale)
        }

    @DeleteMapping("/{id}")
    fun deleteSale(
        @RequestAttribute("employee") employee: Employee,
        @PathVariable("id") id: String
    ) =
        Responser.success {
            assert(employee.permissions.contains(Employee.Permissions.DELETE_SALES))
            salesLogics.delete(id)
        }

}