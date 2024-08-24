package io.github.abdulroufsidhu.ambaar.apis.base.business

import jakarta.transaction.Transactional
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BusinessLogic(
    private val businessDao: BusinessDao,
    private val jdbcTemplate: JdbcTemplate,
) {

    fun insertOrReturnExisting(business: Business): String? {
        if (business.id != null) return business.id!!.toString()
        val sql = """INSERT INTO businesses (
                |id
                |, description
                |, licence_number
                |, name
            |) VALUES (
                |'${business.id ?: UUID.randomUUID()}'
                |, '${business.description}'
                |, '${business.licence}'
                |, '${business.name}'
            |) ON CONFLICT DO NOTHING RETURNING id
        """.trimMargin()
        return jdbcTemplate.queryForObject(sql, String::class.java)
    }

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
        NoSuchElementException::class
    )

    fun update(business: Business): Business {
        if (business.id == null) throw IllegalArgumentException("id not provided")
        return businessDao.save(business)
    }

    fun delete(id: String): Business {
        val business = businessDao.getReferenceById(UUID.fromString(id))
        return businessDao.save(business.copy(active = false))
    }
}