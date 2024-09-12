package io.github.abdulroufsidhu.orgolink.core.person

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.github.abdulroufsidhu.orgolink.core.address.Address
import io.github.abdulroufsidhu.orgolink.core.config.BaseTable
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import java.time.Instant
import java.util.*

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class",
)
@Entity
@Table(name = "persons")
data class Person(
    @Column(name = "first_name")
    @JsonProperty(value = "first_name")
    val firstName: String,
    @Column(name = "last_name")
    @JsonProperty(value = "last_name")
    val lastName: String,
    @OneToMany
    @JsonProperty("phone_number")
    var phoneNumbers: Set<PhoneNumber> = setOf(),
    @OneToMany
    var emails: Set<io.github.abdulroufsidhu.orgolink.core.person.Email> = setOf(),
    @OneToMany
    var nationalities: Set<Nationality> = setOf(),
    @ManyToMany(targetEntity = Address::class, fetch = FetchType.EAGER)
    var address: MutableList<Address?> = mutableListOf(),
    var primaryEmail: String? = null,
) : BaseTable()
