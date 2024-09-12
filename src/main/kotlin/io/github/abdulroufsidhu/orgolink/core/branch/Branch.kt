package io.github.abdulroufsidhu.orgolink.core.branch

import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.github.abdulroufsidhu.orgolink.core.address.Address
import io.github.abdulroufsidhu.orgolink.core.business.Business
import io.github.abdulroufsidhu.orgolink.core.config.BaseTable
import jakarta.persistence.*
import java.time.Instant
import java.util.*


@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class"
)
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

    @ManyToOne(targetEntity = Address::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    var address: Address? = null,

    @ManyToOne(targetEntity = Business::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id")
    var business: Business? = null,

) : BaseTable()