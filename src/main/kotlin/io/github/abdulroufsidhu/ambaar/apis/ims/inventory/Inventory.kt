package io.github.abdulroufsidhu.ambaar.apis.ims.inventory

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.github.abdulroufsidhu.ambaar.apis.base.branch.Branch
import io.github.abdulroufsidhu.ambaar.apis.base.core.BaseTable
import io.github.abdulroufsidhu.ambaar.apis.ims.product.Product
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
@Table(name = "inventory")
data class Inventory(

    var color: String? = null,
    var description: String? = null,
    @Column(name = "quantity_in_stock")
    var quantityInStock: Int? = null,
    @Column(name = "unit_price")
    var unitPrice: Double? = null,
    @Column(name = "allowed_discount_in_percent")
    var allowedDiscountInPercent: Int? = null,
    @Column(name = "purchase_price")
    var purchasePrice: Double? = null,

    @ManyToOne(targetEntity = Branch::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    @JsonIgnore
    var branch: Branch? = null,

    @ManyToOne(targetEntity = Product::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    var product: Product? = null,

    override var id: UUID? = null,
    override var createdAt: Instant? = null,
    override var updatedAt: Instant? = null,
) : BaseTable(id, createdAt, updatedAt)
