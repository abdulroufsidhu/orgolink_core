package io.github.abdulroufsidhu.ambaar.apis.user.person

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.abdulroufsidhu.ambaar.apis.address.Address
import io.github.abdulroufsidhu.ambaar.apis.core.BaseTable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "persons")
data class Person(
    override var id: UUID? = null,
    @Column(name ="first_name")
    @JsonProperty(value = "first_name")
    val firstName: String,
    @Column(name ="last_name")
    @JsonProperty(value = "last_name")
    val lastName: String,
    @Column(name = "contact_number", unique = true)
    @JsonProperty(value = "contact_number")
    val contactNumber: String?,
    @Column(name = "email", unique = true)
    @field:Email(
        message = "Please enter a valid email address",
        regexp = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    )
    @JsonProperty(value = "email")
    val email: String?,
    @Column(name = "national_id", unique = true)
    @JsonProperty(value = "national_id")
    val nationalId: String?,
    @ManyToMany(targetEntity = Address::class, fetch = FetchType.LAZY)
    var address: MutableList<Address?> = mutableListOf(),
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
) : BaseTable(id, createdAt, updatedAt)
