package io.github.abdulroufsidhu.ambaar.apis.branch

import io.github.abdulroufsidhu.ambaar.apis.address.Address
import io.github.abdulroufsidhu.ambaar.apis.business.Business
import io.github.abdulroufsidhu.ambaar.apis.core.BaseTable
import io.github.abdulroufsidhu.ambaar.apis.employee.Employee
import io.github.abdulroufsidhu.ambaar.apis.inventory.Inventory
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.Instant
import java.util.UUID

@Entity
@Table(
    name = "branches",
    uniqueConstraints = [
        UniqueConstraint(name = "unique_branch", columnNames = ["code", "business_id"])
    ]
)
data class Branch(
    var name: String? = null,
    var code: String? = null,
    @Column(unique = true)
    var phone: String? = null,
    @Column(unique = true)
    var email: String? = null,
    var website: String? = null,
    var description: String? = null,

    @Column(name = "active", columnDefinition = "boolean default true not null")
    var active: Boolean = true,

    @ManyToOne(targetEntity = Address::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    var address: Address? = null,

    @ManyToOne(targetEntity = Business::class)
    @JoinColumn(name = "business_id")
    var business: Business? = null,

    @Column(name = "id", nullable = false, updatable = false)
    override var id: UUID? = null,
    @Column(name = "created_at", nullable = false, updatable = false)
    override var createdAt: Instant? = null,
    @Column(name = "updated_at", nullable = false)
    override var updatedAt: Instant? = null,
) : BaseTable(id, createdAt, updatedAt)