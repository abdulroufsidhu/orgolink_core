package io.github.abdulroufsidhu.orgolink.core.ims.sales

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.github.abdulroufsidhu.orgolink.core.config.BaseTable
import io.github.abdulroufsidhu.orgolink.core.ims.inventory.Inventory
import io.github.abdulroufsidhu.orgolink.core.person.Person
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class"
)
@Entity
@Table(name = "sales")
data class Sale(
    @ManyToOne(targetEntity = Person::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    @JsonProperty(value = "sold_to")
    val soldTo: Person,
    @Column(name = "unit_sale_price")
    @JsonProperty(value = "unit_sale_price")
    val unitSalePrice: Double,
    @Column(name = "unit_purchase_price")
    @JsonProperty(value = "unit_purchase_price")
    val unitPurchasePrice: Double,
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "inventory_id")
    val inventory: Inventory,
    @Column(name="quantity")
    val quantity: Int,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val deleted: Boolean = false,
) : BaseTable()