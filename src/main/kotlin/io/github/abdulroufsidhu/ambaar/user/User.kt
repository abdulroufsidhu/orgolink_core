package io.github.abdulroufsidhu.ambaar.user

import io.github.abdulroufsidhu.ambaar.address.Address
import io.github.abdulroufsidhu.ambaar.core.BaseTable
import io.github.abdulroufsidhu.ambaar.employee.Employee
import jakarta.annotation.Nonnull
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import java.time.Instant

@Entity
@Table(name = "users")
data class User(
    @Column(nullable = false, unique = true)
    var username: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false)
    @field:Email(message = "Please enter a valid email address", regexp = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
    var email: String,
    @OneToMany(mappedBy = "user")
    var employeements: List<Employee>? = null,
    @ManyToOne(targetEntity = Address::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    var address: Address,

    override var id: String? = null,
    override var createdAt: Instant?=null,
    override var updatedAt: Instant?=null,
) : BaseTable(id, createdAt, updatedAt)