package io.github.abdulroufsidhu.orgolink.core.person

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.github.abdulroufsidhu.orgolink.core.address.Address
import io.github.abdulroufsidhu.orgolink.core.config.BaseTable
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import java.time.Instant

@Entity
@Table(name = "emails")
data class Email(

    @field:Email(
        message = "Please enter a valid email address",
        regexp = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    )
    @Column(unique = true)
    var email: String = "emaill@example.com",
    var active: Boolean = true,
) : BaseTable()

@Entity
@Table(name = "phone_numbers")
data class PhoneNumber(
    @Column(unique = true)
    var number: String,
    var active: Boolean = true,
) : BaseTable()

@Entity
@Table(name = "nationalities")
data class Nationality(
    @Column(unique = true)
    var number: String,
    @Convert(converter = IdTypeConverter::class)
    var type: IdType = IdType.NIC,
    @Enumerated(EnumType.STRING)
    var country: Address.Country = Address.Country.PAKISTAN,
    @Column(name = "issue_date") @JsonProperty("issue_date")
    var issueDate: Instant,
    @Column(name = "expiry_date") @JsonProperty("expiry_date")
    var expiryDate: Instant,
) : BaseTable() {
    enum class IdType {
        NIC,
        PASSPORT,
    }

    @Converter(autoApply = false)
    class IdTypeConverter : AttributeConverter<IdType, String> {
        private val om = ObjectMapper().apply {
            registerModule(KotlinModule.Builder().build())
        }

        override fun convertToDatabaseColumn(attribute: IdType?): String = om.writeValueAsString(attribute)

        override fun convertToEntityAttribute(dbData: String?): IdType = om.readValue(dbData, IdType::class.java)
    }

}

