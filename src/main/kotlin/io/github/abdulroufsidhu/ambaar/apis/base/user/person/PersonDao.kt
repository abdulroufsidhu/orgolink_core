package io.github.abdulroufsidhu.ambaar.apis.base.user.person

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PersonDao : JpaRepository<Person, UUID>
