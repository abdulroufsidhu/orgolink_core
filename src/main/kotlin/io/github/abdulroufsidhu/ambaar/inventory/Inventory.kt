package io.github.abdulroufsidhu.ambaar.inventory

import io.github.abdulroufsidhu.ambaar.branch.Branch
import io.github.abdulroufsidhu.ambaar.core.BaseTable
import io.github.abdulroufsidhu.ambaar.product.Product
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.Instant

data class Inventory(

    var color: String?,
    var description: String?,
    @Column(name = "quantity_in_stock")
    var quantityInStock: Int?,
    @Column(name = "unit_price")
    var unitPrice: Double?,
    @Column(name = "allowed_discount_in_percent")
    var allowedDiscountInPercent: Int?,
    @Column(name = "purchase_price")
    var purchasePrice: Double?,

    @ManyToOne(targetEntity = Branch::class, fetch = FetchType.EAGER)
    var branch: List<Branch>?,

    @ManyToOne(targetEntity = Product::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    var product: Product?,

    override var id: String?,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
) : BaseTable(id, createdAt, updatedAt)
