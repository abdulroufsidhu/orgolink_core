package io.github.abdulroufsidhu.ambaar.business

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
    fun createBusiness(business: Business) = businessLogic.create(business)

    @PatchMapping("")
    fun updateBusiness(business: Business) = businessLogic.update(business)

    @DeleteMapping("/{id}")
    fun deleteBusiness(@PathVariable("id") id: String) = businessLogic.delete(id)
}