package io.github.abdulroufsidhu.ambaar.apis.product

import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.github.abdulroufsidhu.ambaar.apis.core.BaseTable
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
    override var id: UUID?,
    override var createdAt: Instant?=null,
    override var updatedAt: Instant?=null,
) : BaseTable(id, createdAt, updatedAt)