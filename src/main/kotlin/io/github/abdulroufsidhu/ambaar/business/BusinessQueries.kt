package io.github.abdulroufsidhu.ambaar.business

import org.springframework.data.jpa.repository.JpaRepository

interface BusinessQueries : JpaRepository<Business, String> {
}