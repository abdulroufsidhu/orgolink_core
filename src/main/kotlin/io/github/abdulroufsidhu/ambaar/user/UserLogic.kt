package io.github.abdulroufsidhu.ambaar.user

import io.github.abdulroufsidhu.ambaar.address.AddressLogic
import jakarta.transaction.Transactional
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Service

@Service
class UserLogic(
    private val addressLogic: AddressLogic,
    private val userDao: UserDao,
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
        return userDao.save(user)
    }

    @Throws(
        IllegalArgumentException::class,
        NoSuchElementException::class
    )
    fun signIn(email: String, password: String): User {
        return userDao.findByEmailAndPassword(email, password).orElseThrow()
    }

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
    )
    @Transactional
    fun updateUser(user: User): User {
        if (!user.id.isNullOrBlank())
            throw IllegalArgumentException("User id cannot be null or blank")
        return userDao.save(user)
    }

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
    )
    @Transactional
    fun updatePassword(userId: String, password: String): User {
        val user = userDao.getReferenceById(userId)
        user.password = password
        return userDao.save(user)
    }

}