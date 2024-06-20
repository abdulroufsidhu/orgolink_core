package io.github.abdulroufsidhu.ambaar.product

import io.github.abdulroufsidhu.ambaar.core.Responser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductRequests(
    private val productLogics: ProductLogics
) {

    @GetMapping("")
    fun getProducts(product: Product) = Responser.success {
        productLogics.getProduct(product)
    }

}