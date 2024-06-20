package io.github.abdulroufsidhu.ambaar.product

import org.springframework.stereotype.Service

@Service
class ProductLogics(
    private val productDao: ProductDao
) {

    @Throws(NoSuchElementException::class, IllegalArgumentException::class)
    fun getProduct(product: Product): List<Product> = when {
        product.id != null -> listOf(productDao.getReferenceById(product.id!!))
        product.name != null -> productDao.findByName(product.name!!).orElseThrow()
        product.company != null -> productDao.findByCompany(product.company!!).orElseThrow()
        product.dimentions != null -> productDao.findByDimentions(product.dimentions!!).orElseThrow()
        product.createdAt != null -> productDao.findByCreatedAt(product.createdAt!!).orElseThrow()
        product.weight != null -> productDao.findByWeight(product.weight!!).orElseThrow()
        else -> throw IllegalArgumentException("Invalid product")
    }

    fun saveProduct(product: Product): Product = productDao.save(product)

    fun deleteProduct(product: Product) = productDao.delete(product)

}