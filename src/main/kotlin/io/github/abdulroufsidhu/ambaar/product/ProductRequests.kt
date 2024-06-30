package io.github.abdulroufsidhu.ambaar.product

import io.github.abdulroufsidhu.ambaar.core.Responser
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
@CrossOrigin
class ProductRequests(
    private val productLogic: ProductLogic
) {

    @GetMapping("")
    fun getProducts(product: Product) = Responser.success {
        productLogic.getProductInclusiveId(product)
    }

}