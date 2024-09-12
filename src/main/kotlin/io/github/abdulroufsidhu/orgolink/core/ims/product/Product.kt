package io.github.abdulroufsidhu.orgolink.core.ims.product

import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.github.abdulroufsidhu.orgolink.core.config.BaseTable
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.Instant
import java.util.UUID

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class"
)
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