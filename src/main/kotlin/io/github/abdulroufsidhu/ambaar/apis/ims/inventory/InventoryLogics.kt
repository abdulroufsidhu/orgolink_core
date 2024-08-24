package io.github.abdulroufsidhu.ambaar.apis.ims.inventory

import io.github.abdulroufsidhu.ambaar.apis.ims.product.ProductLogic
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class InventoryLogics(
    private val productLogic: ProductLogic,
    private val inventoryDao: InventoryDao,
) {
    private val perPage = 24

    @Throws(IllegalArgumentException::class, OptimisticLockingFailureException::class)
    fun save(inventory: Inventory): Inventory {
        if (inventory.product == null) throw IllegalArgumentException("Product cannot be null")
        val product = productLogic.saveOrGetProduct(inventory.product!!)
        return inventoryDao.save(inventory.copy(product = product))
    }

    @Throws(IllegalArgumentException::class, OptimisticLockingFailureException::class)
    fun update(inventory: Inventory): Inventory {
        if (inventory.id == null) throw IllegalArgumentException("Id cannot be null")
        val product = productLogic.saveOrGetProduct(inventory.product!!)
        return inventoryDao.save(inventory.copy(product = product))
    }

    @Throws(IllegalArgumentException::class)
    fun find(branchId: UUID?, pageNumber: Int): Page<Inventory> {
        if (branchId == null) throw IllegalArgumentException("Branch cannot be null")
        return inventoryDao.findByBranchId(
            branchId,
            Pageable.ofSize(perPage).withPage(pageNumber)
        ).orElse(Page.empty())
    }

    fun delete(id: String): String {
        inventoryDao.deleteById(UUID.fromString(id))
        return "product deleted successfully"
    }

}