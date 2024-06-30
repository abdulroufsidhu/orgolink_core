package io.github.abdulroufsidhu.ambaar.user

import io.github.abdulroufsidhu.ambaar.user.data_models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserDao : JpaRepository<User, String> {
    fun findByEmailAndPassword(email: String, password: String): Optional<User>

    fun findByEmail(email: String): Optional<User?>

}