package io.github.abdulroufsidhu.ambaar.apis.user.data_models

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.abdulroufsidhu.ambaar.apis.core.BaseTable
import io.github.abdulroufsidhu.ambaar.apis.user.person.Person
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "users")
data class User(
    @OneToOne(targetEntity = Person::class, fetch = FetchType.EAGER,)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    val person: Person,
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private val password: String?,

    var active: Boolean? = true,

    @ElementCollection(targetClass = UserAuthorities::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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

    override fun getUsername(): String = person.email.orEmpty()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = super.isCredentialsNonExpired()

    override fun isEnabled(): Boolean = true

}