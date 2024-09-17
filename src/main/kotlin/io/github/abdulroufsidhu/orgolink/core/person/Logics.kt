package io.github.abdulroufsidhu.orgolink.core.person

import io.github.abdulroufsidhu.orgolink.core.address.Address
import io.github.abdulroufsidhu.orgolink.core.address.AddressLogic
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.util.*

@Component
class ContactInfoLogic(
    private val emailDao: EmailDao,
    private val nationalityDao: NationalityDao,
    private val phoneNumberDao: PhoneNumberDao,
) {
    fun saveEmail(email: Email) = emailDao.save(email)
    fun saveEmail(emails: Iterable<Email>) = emailDao.saveAll(emails)

    fun saveNationality(nationality: Nationality) = nationalityDao.save(nationality)
    fun saveNationality(nationalities: Iterable<Nationality>) = nationalityDao.saveAll(nationalities)

    fun savePhoneNumber(phoneNumber: PhoneNumber) = phoneNumberDao.save(phoneNumber)
    fun savePhoneNumber(phoneNumbers: Iterable<PhoneNumber>) = phoneNumberDao.saveAll(phoneNumbers)

}

@Component
class PersonLogic(
    private val contactInfoLogic: ContactInfoLogic,
    private val personDao: PersonDao,
    private val addressLogic: AddressLogic,
) {
    fun insertOrRetrieve(person: Person) =
        search(person, Pageable.ofSize(1).withPage(0)).content.firstOrNull() ?: save(person)

    fun search (person: Person, pageable: Pageable) =
        personDao.findAll(Example.of(person, ExampleMatcher.matching().withIncludeNullValues()), pageable)


    fun save(person: Person): Person {
        val addresses = person.address.mapNotNull { addr ->
            addr?.let { nonNullAddr ->
                addressLogic.insertOrReturnExisting(nonNullAddr)
            }
        }.map { Address().apply { id = it } }.toSet()

        val emails = contactInfoLogic.saveEmail(person.emails).toSet()
        val phones = contactInfoLogic.savePhoneNumber(person.phoneNumbers).toSet()
        val nationalities = contactInfoLogic.saveNationality(person.nationalities).toSet()

        return personDao.save(person.copy(address = addresses, emails = emails, phoneNumbers = phones, nationalities = nationalities))
    }
    fun update(person: Person) {

    }
}