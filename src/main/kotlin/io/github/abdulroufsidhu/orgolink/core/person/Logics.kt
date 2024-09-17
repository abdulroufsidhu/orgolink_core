package io.github.abdulroufsidhu.orgolink.core.person

import io.github.abdulroufsidhu.orgolink.core.address.Address
import io.github.abdulroufsidhu.orgolink.core.address.AddressLogic
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class ContactInfoLogic(
    private val emailDao: EmailDao,
    private val nationalityDao: NationalityDao,
    private val phoneNumberDao: PhoneNumberDao,
) {
    fun saveEmail(email: Email) = emailDao.save(email)
    fun saveNationality(nationality: Nationality) = nationalityDao.save(nationality)
    fun savePhoneNumber(phoneNumber: PhoneNumber) = phoneNumberDao.save(phoneNumber)

    fun saveEmail(emails: Iterable<Email>) = emails.map {
        try {
            emailDao.save(it)
        } catch (_: Exception) {
            emailDao.findBy(Example.of(it)) { qo->
                qo.firstValue()
            }
        }
    }.toSet()
    fun saveNationality(nationalities: Iterable<Nationality>) = nationalities.map {
        try {
            nationalityDao.save(it)
        } catch (_: Exception) {
            nationalityDao.findBy(Example.of(it)) {qo ->
                qo.firstValue()
            }
        }
    }.toSet()
    fun savePhoneNumber(phoneNumbers: Iterable<PhoneNumber>) = phoneNumbers.map {
        try {
            phoneNumberDao.save(it)
        } catch (_: Exception) {
            phoneNumberDao.findBy(Example.of(it)) {qo ->
                qo.firstValue()
            }
        }
    }.toSet()

}

@Component
class PersonLogic(
    private val contactInfoLogic: ContactInfoLogic,
    private val personDao: PersonDao,
    private val addressLogic: AddressLogic,
) {
    fun insertOrRetrieve(person: Person) =
        search(person, Pageable.ofSize(1).withPage(0)).content.firstOrNull() ?: save(person)

    fun search(person: Person, pageable: Pageable) =
        personDao.findAll(Example.of(person, ExampleMatcher.matching().withIncludeNullValues()), pageable)


    fun save(person: Person): Person {
        val addresses = person.address.mapNotNull { addr ->
            addr?.let { addressLogic.insertOrReturnExisting(it) }
        }.map { Address().apply { id = it } }.toSet()

        val emails = contactInfoLogic.saveEmail(person.emails).toSet()
        val phones = contactInfoLogic.savePhoneNumber(person.phoneNumbers).toSet()
        val nationalities = contactInfoLogic.saveNationality(person.nationalities).toSet()

        return personDao.save(
            person.copy(
                address = addresses,
                emails = emails,
                phoneNumbers = phones,
                nationalities = nationalities
            )
        )
    }

    fun update(person: Person) = save(person)

}