package io.github.abdulroufsidhu.ambaar.branch

import org.springframework.data.jpa.repository.JpaRepository

interface BranchQueries: JpaRepository<Branch, String> {
}