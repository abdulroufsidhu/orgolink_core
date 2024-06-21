package io.github.abdulroufsidhu.ambaar.inventory

import io.github.abdulroufsidhu.ambaar.core.Responser
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/inventory")
@CrossOrigin
class InventoryRequests(
    private val inventoryLogics: InventoryLogics
) {
    @GetMapping("/{page}")
    fun getInventory(
        @PathVariable("page") page: Int,
        @RequestParam("inventory") inventory: Inventory
    ) = Responser.success {
        inventoryLogics.find(inventory, page)
    }

    @PutMapping("")
    fun putInventory(
        @RequestBody inventory: Inventory
    ) = Responser.success{
        inventoryLogics.save(inventory)
    }

    @PatchMapping("")
    fun updateInventory(
        @RequestBody inventory: Inventory
    ) = Responser.success {
        inventoryLogics.update(inventory)
    }

    @DeleteMapping("/{id}")
    fun deleteInventory(
        @PathVariable("id") id: String
    ) = Responser.success { inventoryLogics.delete(id) }

}