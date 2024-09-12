package io.github.abdulroufsidhu.orgolink.core.person

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PersonDao : JpaRepository<Person, UUID>
