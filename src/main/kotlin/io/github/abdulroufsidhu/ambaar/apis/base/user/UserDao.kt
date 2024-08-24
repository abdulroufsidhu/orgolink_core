package io.github.abdulroufsidhu.ambaar.apis.base.user

import io.github.abdulroufsidhu.ambaar.apis.base.user.data_models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional
import java.util.UUID

interface UserDao : JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE UPPER(u.person.email) = UPPER(?1) AND u.password=?2")
    fun findByEmailAndPassword(email: String, password: String): Optional<User>

    @Query("SELECT u FROM User u WHERE UPPER(u.person.email) = UPPER(?1)")
    fun findByEmail(email: String): Optional<User?>


}