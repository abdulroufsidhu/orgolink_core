package io.github.abdulroufsidhu.orgolink.core.ims.product

import io.github.abdulroufsidhu.orgolink.core.config.BaseTable
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint


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
) : BaseTable()