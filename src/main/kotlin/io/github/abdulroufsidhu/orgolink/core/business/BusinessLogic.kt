package io.github.abdulroufsidhu.orgolink.core.business

import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Pageable
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class BusinessLogic(
    private val businessDao: BusinessDao,
) {

    fun insertOrReturnExisting(business: Business) =
        searchBusiness(business, Pageable.ofSize(1).withPage(0)).content.firstOrNull()
            ?: businessDao.save(business)

    fun searchBusiness(business: Business, pageable: Pageable) =
        businessDao.findAll(Example.of(business, ExampleMatcher.matching().withIgnoreNullValues()), pageable)

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