package io.github.abdulroufsidhu.orgolink.core.user

import io.github.abdulroufsidhu.orgolink.core.config.auth.AuthService
import io.github.abdulroufsidhu.orgolink.core.person.PersonLogic
import io.github.abdulroufsidhu.orgolink.core.user.requests.SignInRequest
import io.github.abdulroufsidhu.orgolink.core.user.requests.SignInResponse
import org.slf4j.LoggerFactory
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserLogic(
    private val userDao: UserDao,
    private val personLogic: PersonLogic,
    private val encoder: PasswordEncoder,
    private val authService: AuthService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun insertOrReturnExisting(user: User): User = personLogic.insertOrRetrieve(user.person).let { p->
        searchUser(user.copy(person = p), Pageable.ofSize(1).withPage(0)).content.firstOrNull() ?: save(user.copy(person = p))
    }

    fun searchUser(user: User, pageable: Pageable) =
        userDao.findAll(Example.of(user, ExampleMatcher.matching().withIgnoreNullValues()), pageable)

    fun save(user: User): User {
        val p = personLogic.insertOrRetrieve(user.person)
        return userDao.save(user.copy(person = p))
    }

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
        NoSuchElementException::class,
    )
    fun createUser(user: User): SignInResponse {
        val u = insertOrReturnExisting(
            user.copy(
                password = encoder.encode(user.password),
                primaryEmail = user.person.emails.firstOrNull()?.email
            )
        )
        return authService.authentication(SignInRequest(u.username, user.password))
    }

    @Throws( IllegalArgumentException::class, NoSuchElementException::class )
    fun signIn(signInRequest: SignInRequest): SignInResponse {
        val signInResponse = authService.authentication(signInRequest)
        logger.info("signInResponse: $signInResponse")
        return signInResponse
    }

    @Throws( IllegalArgumentException::class, OptimisticLockingFailureException::class, )
    fun updateUser(user: User): User {
        if (user.id != null) throw IllegalArgumentException("User id cannot be null or blank")
        return userDao.save(user)
    }

    @Throws( IllegalArgumentException::class,OptimisticLockingFailureException::class, )
    fun updatePassword(userId: UUID, password: String): User {
        val user = userDao.getReferenceById(userId)
        return userDao.save(user.copy(password = encoder.encode(password)))
    }

}