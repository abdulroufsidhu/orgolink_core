package io.github.abdulroufsidhu.orgolink.core.ims.inventory

import com.fasterxml.jackson.annotation.JsonIgnore
import io.github.abdulroufsidhu.orgolink.core.branch.Branch
import io.github.abdulroufsidhu.orgolink.core.config.BaseTable
import io.github.abdulroufsidhu.orgolink.core.ims.product.Product
import jakarta.persistence.*


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

    ) : BaseTable()
