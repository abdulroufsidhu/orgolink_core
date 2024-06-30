package io.github.abdulroufsidhu.ambaar.user.data_models

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.abdulroufsidhu.ambaar.address.Address
import io.github.abdulroufsidhu.ambaar.core.BaseTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant

@Entity
@Table(name = "users")
data class User(
    @Column(nullable = false, unique = true)
    val fullName: String,
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private val password: String,
    @Column(nullable = false)
    @field:Email(
        message = "Please enter a valid email address",
        regexp = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    )
    private val email: String,
    @ManyToOne(targetEntity = Address::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    var address: Address,

    var active: Boolean? = true,

    @ElementCollection(targetClass = UserAuthorities::class)
    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var authorities: List<UserAuthorities> = listOf(UserAuthorities.USER),

    override var id: String? = null,
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

    override fun getPassword(): String = password

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = super.isCredentialsNonExpired()

    override fun isEnabled(): Boolean = true


}