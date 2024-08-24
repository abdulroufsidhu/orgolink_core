package io.github.abdulroufsidhu.ambaar.apis.ims.product

import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant
import java.util.Optional
import java.util.UUID

interface ProductDao : JpaRepository<Product, UUID> {

    fun findByName(productName: String): Optional<List<Product>>
    fun findByDimentions(productDimentions: String): Optional<List<Product>>
    fun findByCreatedAt(productCreatedAt: Instant): Optional<List<Product>>
    fun findByCompany(productCompany: String): Optional<List<Product>>
    fun findByWeight(weight: String): Optional<List<Product>>


}