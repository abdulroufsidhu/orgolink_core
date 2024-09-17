package io.github.abdulroufsidhu.orgolink.core.person

import io.github.abdulroufsidhu.orgolink.core.config.Responser
import io.github.abdulroufsidhu.orgolink.core.user.User
import org.springframework.data.domain.Pageable
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
class PersonRequests(
    private val personLogic: PersonLogic,
    private val contactInfoLogic: ContactInfoLogic,
) {

    @GetMapping()
    fun getProfile(
        @AuthenticationPrincipal user: User,
        @RequestParam("page_size") pageSize: Int,
        @RequestParam("page") page: Int,
        person: Person
    ) = Responser.success { personLogic.search(person = person, Pageable.ofSize(pageSize).withPage(page)) }

    @PatchMapping()
    fun updateProfile(
        @AuthenticationPrincipal user: User,
        person: Person,
    ) = Responser.success { personLogic.update(person.apply { id = user.person.id }) }

    @PostMapping("/email")
    fun addEmail(
        @AuthenticationPrincipal user: User,
        email: String,
    ) = Responser.success {
        personLogic.update(
            user.person.copy(
                emails = setOf(
                    *user.person.emails.toTypedArray(),
                    Email(email)
                )
            )
        )
    }

    @DeleteMapping("/email")
    fun removeEmail(
        @AuthenticationPrincipal user: User,
        email: String,
    ) = Responser.success {
        personLogic.update(user.person.copy(emails = user.person.emails.filter { it.email != email }.toSet()))
    }

    @PostMapping("/phone")
    fun addPhoneNumber(
        @AuthenticationPrincipal user: User,
        phone: String,
    ) = Responser.success {
        personLogic.update(
            user.person.copy(
                phoneNumbers = setOf(
                    *user.person.phoneNumbers.toTypedArray(),
                    PhoneNumber(phone)
                )
            )
        )
    }

    @DeleteMapping("/phone")
    fun removePhoneNumber(
        @AuthenticationPrincipal user: User,
        phone: String,
    ) = Responser.success {
        personLogic.update(user.person.copy(phoneNumbers = user.person.phoneNumbers.filter { it.number != phone }
            .toSet()))
    }

    @PostMapping("/nationality")
    fun addNationality(
        @AuthenticationPrincipal user: User,
        nationality: Nationality,
    ) = Responser.success {
        personLogic.update(
            user.person.copy(
                nationalities = setOf(
                    *user.person.nationalities.toTypedArray(),
                    nationality
                )
            )
        )
    }

    @DeleteMapping("/nationality")
    fun removeNationality(
        @AuthenticationPrincipal user: User,
        id: String,
    ) = Responser.success {
        personLogic.update(user.person.copy(nationalities = user.person.nationalities.filter {
            it.id != UUID.fromString(id)
        }.toSet()))
    }

}