package io.github.abdulroufsidhu.ambaar.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserDao : JpaRepository<User, String> {
    fun findByEmailAndPassword(email: String, password: String): Optional<User>
}