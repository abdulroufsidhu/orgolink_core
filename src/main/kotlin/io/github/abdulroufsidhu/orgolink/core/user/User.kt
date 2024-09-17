package io.github.abdulroufsidhu.orgolink.core.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.github.abdulroufsidhu.orgolink.core.config.BaseTable
import io.github.abdulroufsidhu.orgolink.core.person.Person
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name = "users")
data class User(
    @OneToOne(targetEntity = Person::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    val person: Person,
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private val password: String?,

    @Parameter(hidden = true)
    @Column(unique = true, name = "primary_email") @JsonProperty("primary_email")
    var primaryEmail: String? = null,

    var active: Boolean? = true,

    @ElementCollection(targetClass = UserAuthorities::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    var authorities: Set<UserAuthorities> = setOf(UserAuthorities.USER),


    ) : BaseTable(), UserDetails {


    enum class UserAuthorities {
        USER,
        ADMIN,
        SUPER_ADMIN,
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        authorities.map { SimpleGrantedAuthority(it.name) }.toMutableList()

    override fun getPassword(): String = password.orEmpty()

    @Parameter(hidden = true)
    override fun getUsername(): String = primaryEmail.orEmpty()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = super.isCredentialsNonExpired()

    override fun isEnabled(): Boolean = true

}