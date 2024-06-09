package io.github.abdulroufsidhu.ambaar.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserQueries : JpaRepository<User, String> {
    fun findByEmailAndPassword(email: String, password: String)
}