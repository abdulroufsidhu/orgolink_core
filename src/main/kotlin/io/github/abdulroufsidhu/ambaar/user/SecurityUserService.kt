package io.github.abdulroufsidhu.ambaar.user

import io.github.abdulroufsidhu.ambaar.user.data_models.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = User
@Service
class SecurityUserService(
    private val userRepository: UserDao
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByEmail(username).orElseThrow()
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("Not found!")
    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User(
            email = this.username,
            password = this.password,
            address = this.address,
            active = this.active,
            fullName = this.fullName,
        )
}