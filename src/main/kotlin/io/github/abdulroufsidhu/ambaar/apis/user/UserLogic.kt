package io.github.abdulroufsidhu.ambaar.apis.user

import io.github.abdulroufsidhu.ambaar.apis.address.Address
import io.github.abdulroufsidhu.ambaar.apis.address.AddressLogic
import io.github.abdulroufsidhu.ambaar.apis.core.auth.AuthService
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.request.SignInRequest
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.request.SignInResponse
import io.github.abdulroufsidhu.ambaar.apis.user.person.PersonDao
import io.github.abdulroufsidhu.ambaar.apis.user.person.PersonLogic
import jakarta.transaction.Transactional
import org.hibernate.Hibernate
import org.slf4j.LoggerFactory
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.jdbc.core.JdbcTemplate
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
    private val personLogic: PersonLogic,
    private val jdbcTemplate: JdbcTemplate,
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun insertOrReturnExisting(user: User): String? {
        if (user.id != null) return user.id?.toString()

        Hibernate.initialize(user.person)
        val personId = personLogic.insertOrReturnExisting(user.person)
        val sql = """
            INSERT INTO users (
                id
                , person_id
            ) VALUES (
                '${UUID.randomUUID()}'
                , '${personId}'
            ) ON CONFLICT DO NOTHING RETURNING id
        """.trimIndent()
        return jdbcTemplate.queryForObject(sql, String::class.java)
    }

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
        NoSuchElementException::class,
    )
    @Transactional
    fun createUser(user: User): SignInResponse {
        val addr = user.person.address
        val addressId = addressLogic.insertOrReturnExisting(addr.first()!!)!!
        user.person.address = mutableListOf(Address(id = UUID.fromString(addressId)))
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