package io.github.abdulroufsidhu.orgolink.core.person

import io.github.abdulroufsidhu.orgolink.core.address.Address
import io.github.abdulroufsidhu.orgolink.core.config.BaseTable
import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Entity
@Table(name="emails")
data class Email(

    @field:Email(
        message = "Please enter a valid email address",
        regexp = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    )
    @Column(unique = true)
    var email: String,
    var active: Boolean = true,
): BaseTable()

@Entity
@Table(name="phone_numbers")
data class PhoneNumber(
    @Column(unique = true)
    var number: String,
    var active: Boolean = true,
): BaseTable()

@Entity
@Table(name = "nationalities")
data class Nationality(
    @Column(unique = true)
    var number: String,
    @Enumerated(EnumType.STRING)
    var type: IdType,
    @Enumerated(EnumType.STRING)
    var country: Address.Country,
) : BaseTable() {
    enum class IdType {
        NIC,
        PASSPORT,
    }
}

