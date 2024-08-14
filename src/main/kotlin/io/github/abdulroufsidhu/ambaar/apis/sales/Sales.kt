package io.github.abdulroufsidhu.ambaar.apis.sales

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.abdulroufsidhu.ambaar.apis.core.BaseTable
import io.github.abdulroufsidhu.ambaar.apis.inventory.Inventory
import io.github.abdulroufsidhu.ambaar.apis.user.person.Person
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "sales")
data class Sales(
    @ManyToOne(targetEntity = Person::class)
    @JoinColumn(name = "person_id")
    @JsonProperty(value = "sold_to")
    val soldTo: Person,
    @Column(name = "sale_price")
    @JsonProperty(value = "sale_price")
    val salePrice: Double,
    @Column(name = "purchase_price")
    @JsonProperty(value = "purchase_price")
    val purchasePrice: Double,
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    val inventory: Inventory,
    override var id: UUID? = null,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
) : BaseTable(id, createdAt, updatedAt)