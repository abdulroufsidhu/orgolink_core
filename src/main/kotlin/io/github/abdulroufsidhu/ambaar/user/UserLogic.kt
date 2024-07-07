package io.github.abdulroufsidhu.ambaar.user

import io.github.abdulroufsidhu.ambaar.address.AddressLogic
import io.github.abdulroufsidhu.ambaar.core.auth.AuthService
import io.github.abdulroufsidhu.ambaar.user.data_models.SignInRequest
import io.github.abdulroufsidhu.ambaar.user.data_models.User
import jakarta.transaction.Transactional
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserLogic(
    private val addressLogic: AddressLogic,
    private val userDao: UserDao,
    private val encoder: PasswordEncoder,
    private val authService: AuthService,
) {

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
        NoSuchElementException::class,
    )
    @Transactional
    fun createUser(user: User): User {
        if (user.address == null) throw IllegalArgumentException("Address cannot be null")
        val address = addressLogic.saveOrFind(user.address!!)
        user.address = address
        return userDao.save(user.copy(password = encoder.encode(user.password) ))
    }

    @Throws(
        IllegalArgumentException::class,
        NoSuchElementException::class
    )
    fun signIn(signInRequest: SignInRequest): String {
        return authService.authentication(signInRequest)
    }

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
    )
    @Transactional
    fun updateUser(user: User): User {
        if (user.id != null)
            throw IllegalArgumentException("User id cannot be null or blank")
        return userDao.save(user)
    }

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
    )
    @Transactional
    fun updatePassword(userId: String, password: String): User {
        val user = userDao.getReferenceById(UUID.fromString(userId))
        return userDao.save(user.copy(password = encoder.encode(password)))
    }

}