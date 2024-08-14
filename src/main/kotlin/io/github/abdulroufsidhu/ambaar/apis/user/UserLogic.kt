package io.github.abdulroufsidhu.ambaar.apis.user

import io.github.abdulroufsidhu.ambaar.apis.address.AddressLogic
import io.github.abdulroufsidhu.ambaar.apis.core.auth.AuthService
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.request.SignInRequest
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.request.SignInResponse
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User
import io.github.abdulroufsidhu.ambaar.apis.user.person.PersonDao
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserLogic(
    private val addressLogic: AddressLogic,
    private val userDao: UserDao,
    private val personDao: PersonDao,
    private val encoder: PasswordEncoder,
    private val authService: AuthService,
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
        NoSuchElementException::class,
    )
    @Transactional
    fun createUser(user: User): SignInResponse {
        val addr = user.person.address
        val addresses = addressLogic.saveOrFind(addr)
        user.person.address = addresses
        val p = personDao.save(user.person)
        val u = userDao.save(user.copy(password = encoder.encode(user.password), person = p))
        return authService.authentication(SignInRequest(u.username, user.password))
    }

    @Throws(
        IllegalArgumentException::class,
        NoSuchElementException::class
    )
    fun signIn(signInRequest: SignInRequest): SignInResponse {
        val signInResponse = authService.authentication(signInRequest)
        logger.info("signInResponse: $signInResponse")
        return signInResponse
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