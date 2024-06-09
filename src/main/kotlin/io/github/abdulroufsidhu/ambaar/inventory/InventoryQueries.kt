package io.github.abdulroufsidhu.ambaar.inventory

import org.springframework.data.jpa.repository.JpaRepository

interface InventoryQueries: JpaRepository<Inventory, String> {
}