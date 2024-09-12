package io.github.abdulroufsidhu.orgolink.core.ims.product

import org.springframework.stereotype.Service

@Service
class ProductLogic(
    private val productDao: ProductDao
) {

    @Throws(NoSuchElementException::class, IllegalArgumentException::class)
    fun getProductInclusiveId(product: Product): List<Product> = when {
        product.id != null -> listOf(productDao.getReferenceById(product.id!!))
        else -> getProductExcluciveId(product)
    }

    @Throws(NoSuchElementException::class, IllegalArgumentException::class)
    fun getProductExcluciveId(product: Product): List<Product> = when {
        product.name != null -> productDao.findByName(product.name!!).orElseThrow()
        product.company != null -> productDao.findByCompany(product.company!!).orElseThrow()
        product.dimentions != null -> productDao.findByDimentions(product.dimentions!!).orElseThrow()
        product.weight != null -> productDao.findByWeight(product.weight!!).orElseThrow()
        else -> throw IllegalArgumentException("Invalid product")
    }

    fun saveOrGetProduct(product: Product): Product = getProductExcluciveId(product).ifEmpty { listOf(saveProduct(product)) }.first()

    fun saveProduct(product: Product): Product = productDao.save(product)

    private fun deleteProduct(product: Product) = productDao.delete(product)

}