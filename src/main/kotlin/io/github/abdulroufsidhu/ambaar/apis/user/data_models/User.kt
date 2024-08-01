package io.github.abdulroufsidhu.ambaar.apis.user.data_models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.github.abdulroufsidhu.ambaar.apis.address.Address
import io.github.abdulroufsidhu.ambaar.apis.core.BaseTable
import io.github.abdulroufsidhu.ambaar.apis.employee.Employee
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.annotation.Nullable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "users")
data class User(
    @Column(nullable = false, unique = false)
    val fullName: String?,
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private val password: String?,
    @Column(nullable = false)
    @field:Email(
        message = "Please enter a valid email address",
        regexp = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    )
    private val email: String?,
    @ManyToOne(targetEntity = Address::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    var address: Address?,

    var active: Boolean? = true,

    @ElementCollection(targetClass = UserAuthorities::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY,)
    var authorities: Set<UserAuthorities> = setOf(UserAuthorities.USER),

    override var id: UUID? = null,
    override var createdAt: Instant? = null,
    override var updatedAt: Instant? = null,
) : BaseTable(id, createdAt, updatedAt), UserDetails {

    enum class UserAuthorities {
        USER,
        ADMIN,
        SUPER_ADMIN,
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(* authorities.map { SimpleGrantedAuthority(it.name) }.toTypedArray())

    override fun getPassword(): String = password.orEmpty()

    override fun getUsername(): String = email.orEmpty()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = super.isCredentialsNonExpired()

    override fun isEnabled(): Boolean = true

}