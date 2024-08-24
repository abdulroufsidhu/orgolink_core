package io.github.abdulroufsidhu.ambaar.apis.base.user.person

import io.github.abdulroufsidhu.ambaar.apis.base.address.AddressLogic
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PersonLogic(
    private val addressLogic: AddressLogic,
    private val jdbcTemplate: JdbcTemplate,
) {
    fun insertOrReturnExisting(p: Person): String {
        if (p.id != null) return p.id.toString()
        val addresses =
            p.address.mapNotNull { addr -> addr?.let { addressLogic.insertOrReturnExisting(it) } }
        val sql =
            """INSERT INTO persons (
                |id, 
                |contact_number, 
                |email, 
                |first_name, 
                |last_name, 
                |national_id
            |)
            | VALUES (
                | '${UUID.randomUUID()}'
                | ,'${p.contactNumber}'
                | , '${p.email}'
                | , '${p.firstName}'
                | , '${p.lastName}'
                | , '${p.nationalId}'
            | ) 
            | ON CONFLICT DO NOTHING RETURNING id""".trimMargin()
        val personId = jdbcTemplate.queryForObject(sql, String::class.java)!!
        relateAddressesToPerson(personId, addresses)
        return personId
    }

    private fun relateAddressesToPerson(personId: String, addresses: List<String>): MutableList<String> {
        val sql = """INSERT INTO persons_address 
            |(
                |address_id
                |, person_id
            |) VALUES ${
            addresses.mapIndexed { index, it ->
                "('$it','$personId') ${if (index < addresses.size - 1) "," else ""}"
            }
        } 
            |ON CONFLICT DO NOTHING RETURNING address_id
        """.trimMargin()
        return jdbcTemplate.queryForList(sql, String::class.java)
    }
}