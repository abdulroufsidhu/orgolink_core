package io.github.abdulroufsidhu.orgolink.core.person

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.util.UUID

interface PersonDao : JpaRepository<Person, UUID>
interface NationalityDao: JpaRepository<Nationality, UUID>
interface PhoneNumberDao: JpaRepository<PhoneNumber, UUID>
interface EmailDao: JpaRepository<Email, UUID>