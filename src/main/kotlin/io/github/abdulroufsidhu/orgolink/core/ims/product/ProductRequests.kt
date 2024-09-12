package io.github.abdulroufsidhu.orgolink.core.ims.product

import io.github.abdulroufsidhu.orgolink.core.config.Responser
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
@CrossOrigin
class ProductRequests(
    private val productLogic: ProductLogic
) {

    @GetMapping("")
    fun getProducts(product: Product) = Responser.success {
        productLogic.getProductInclusiveId(product)
    }

}