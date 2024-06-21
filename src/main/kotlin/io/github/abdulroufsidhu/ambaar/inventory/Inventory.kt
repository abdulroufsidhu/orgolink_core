package io.github.abdulroufsidhu.ambaar.inventory

import io.github.abdulroufsidhu.ambaar.branch.Branch
import io.github.abdulroufsidhu.ambaar.core.BaseTable
import io.github.abdulroufsidhu.ambaar.product.Product
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant

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
    var branch: Branch,

    @ManyToOne(targetEntity = Product::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    var product: Product? = null,

    override var id: String? = null,
    override var createdAt: Instant? = null,
    override var updatedAt: Instant? = null,
) : BaseTable(id, createdAt, updatedAt)
