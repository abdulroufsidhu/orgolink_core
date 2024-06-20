package io.github.abdulroufsidhu.ambaar.business

import io.github.abdulroufsidhu.ambaar.core.Responser
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/business")
class BusinessRequests(
    private val businessLogic: BusinessLogic
) {

    @PutMapping("")
    fun createBusiness(business: Business) = Responser.success {
        businessLogic.create(business)
    }

    @PatchMapping("")
    fun updateBusiness(business: Business) = Responser.success {
        businessLogic.update(business)
    }

    @DeleteMapping("/{id}")
    fun deleteBusiness(@PathVariable("id") id: String) =
        Responser.success {
            businessLogic.delete(id)
        }
}