package io.github.abdulroufsidhu.ambaar.business

import io.github.abdulroufsidhu.ambaar.branch.BranchLogic
import jakarta.transaction.Transactional
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Service

@Service
class BusinessLogic(
    private val businessDao: BusinessDao,
    private val branchLogic: BranchLogic,
) {

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
        NoSuchElementException::class
    )
    @Transactional
    fun create(business: Business) {
        business.branches?.first()?.let { branchLogic.create(it) }
            ?: throw IllegalArgumentException("branch info not provided")
        businessDao.save(business)
    }

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
        NoSuchElementException::class
    )
    @Transactional
    fun update(business: Business): Business {
        if (business.id.isNullOrBlank()) throw IllegalArgumentException("id not provided")
        return businessDao.save(business)
    }
}