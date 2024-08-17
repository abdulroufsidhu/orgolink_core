package io.github.abdulroufsidhu.ambaar.apis.sales

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.abdulroufsidhu.ambaar.apis.core.BaseTable
import io.github.abdulroufsidhu.ambaar.apis.inventory.Inventory
import io.github.abdulroufsidhu.ambaar.apis.user.person.Person
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "sales")
data class Sale(
    @ManyToOne(targetEntity = Person::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    @JsonProperty(value = "sold_to")
    val soldTo: Person,
    @Column(name = "unit_sale_price")
    @JsonProperty(value = "unit_sale_price")
    val unitSalePrice: Double,
    @Column(name = "unit_purchase_price")
    @JsonProperty(value = "unit_purchase_price")
    val unitPurchasePrice: Double,
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    val inventory: Inventory,
    @Column(name="quantity")
    val quantity: Int,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val deleted: Boolean = false,

    override var id: UUID? = null,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
) : BaseTable(id, createdAt, updatedAt)