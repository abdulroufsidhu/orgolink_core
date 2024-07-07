package io.github.abdulroufsidhu.ambaar.user

import io.github.abdulroufsidhu.ambaar.user.data_models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface UserDao : JpaRepository<User, UUID> {
    fun findByEmailAndPassword(email: String, password: String): Optional<User>

    fun findByEmail(email: String): Optional<User?>

}