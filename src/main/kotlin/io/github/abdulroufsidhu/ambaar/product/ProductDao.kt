package io.github.abdulroufsidhu.ambaar.product

import org.springframework.data.jpa.repository.JpaRepository

interface ProductDao : JpaRepository<Product, String> {
}