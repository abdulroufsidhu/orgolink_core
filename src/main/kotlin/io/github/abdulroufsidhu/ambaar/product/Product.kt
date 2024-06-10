package io.github.abdulroufsidhu.ambaar.product

import io.github.abdulroufsidhu.ambaar.core.BaseTable
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.Instant

@Entity
@Table(
    name = "products",
    uniqueConstraints = [
        UniqueConstraint(name = "product_name_company_unique", columnNames = ["name", "company"])
    ]
)
data class Product(
    var name: String?,
    var company: String?,
    var weight: String?,
    var dimentions: String?,
    override var id: String?,
    override var createdAt: Instant?=null,
    override var updatedAt: Instant?=null,
) : BaseTable(id, createdAt, updatedAt)