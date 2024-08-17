package io.github.abdulroufsidhu.ambaar.apis.user

import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User
import jakarta.transaction.Transactional
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SecurityUserService(
    private val userRepository: UserDao
) : UserDetailsService {

    @Transactional()
    override fun loadUserByUsername(username: String): User =
        userRepository.findByEmail(username).orElseThrow()
            ?: throw UsernameNotFoundException("Not found!")
}