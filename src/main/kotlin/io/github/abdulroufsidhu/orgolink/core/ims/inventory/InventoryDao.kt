package io.github.abdulroufsidhu.orgolink.core.ims.inventory

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface InventoryDao: JpaRepository<Inventory, UUID> {
    fun findByBranchId(branchId: UUID, pageable: Pageable): Optional<Page<Inventory>>
}