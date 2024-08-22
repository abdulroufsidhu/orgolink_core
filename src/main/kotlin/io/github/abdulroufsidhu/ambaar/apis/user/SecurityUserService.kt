package io.github.abdulroufsidhu.ambaar.apis.user

import io.github.abdulroufsidhu.ambaar.apis.core.caching.HibernateInitializer
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SecurityUserService(
    private val userRepository: UserDao,
) : UserDetailsService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Cacheable(value = ["user"], key = "#username")
    override fun loadUserByUsername(username: String): User {
        val user = userRepository.findByEmail(username).orElseThrow()
            ?: throw UsernameNotFoundException("Not found!")
        HibernateInitializer.initializeIfAlreadyNot(user.authorities, user.person, user.person.address)
        logger.info("found use: $user")
        return user
    }
}