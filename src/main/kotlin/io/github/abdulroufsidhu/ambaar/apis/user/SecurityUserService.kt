package io.github.abdulroufsidhu.ambaar.apis.user

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SecurityUserService(
    private val userRepository: UserDao
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByEmail(username).orElseThrow()
            ?: throw UsernameNotFoundException("Not found!")
}