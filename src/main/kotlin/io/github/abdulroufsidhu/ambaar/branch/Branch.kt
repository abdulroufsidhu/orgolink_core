package io.github.abdulroufsidhu.ambaar.branch

import io.github.abdulroufsidhu.ambaar.address.Address
import io.github.abdulroufsidhu.ambaar.business.Business
import io.github.abdulroufsidhu.ambaar.core.BaseTable
import io.github.abdulroufsidhu.ambaar.employee.Employee
import io.github.abdulroufsidhu.ambaar.inventory.Inventory
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.Instant

@Entity
@Table(
    name = "branches",
    uniqueConstraints = [
        UniqueConstraint(name = "unique_code", columnNames = ["code", "business_id"])
    ]
)
data class Branch(
    var name: String,
    var code: String,
    @Column(unique = true)
    var phone: String,
    @Column(unique = true)
    var email: String,
    var website: String,
    var description: String,

    @ManyToOne(targetEntity = Address::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    var address: Address,

    @ManyToOne(targetEntity = Business::class)
    @JoinColumn(name = "business_id")
    var business: Business,

    @OneToMany(mappedBy = "branch")
    var inventory: List<Inventory>,

    @OneToMany(mappedBy = "branch")
    var employees: List<Employee>,

    override var id: String? = null,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
) : BaseTable(id, createdAt, updatedAt)