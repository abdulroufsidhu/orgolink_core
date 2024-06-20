package io.github.abdulroufsidhu.ambaar.inventory

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface InventoryDao: JpaRepository<Inventory, String> {
    fun findByBranchId(branchId: String, pageable: Pageable): Optional<Page<Inventory>>
}