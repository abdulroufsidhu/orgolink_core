package io.github.abdulroufsidhu.orgolink.core.branch

import io.github.abdulroufsidhu.orgolink.core.address.Address
import io.github.abdulroufsidhu.orgolink.core.address.AddressLogic
import io.github.abdulroufsidhu.orgolink.core.business.BusinessLogic
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class BranchLogic(
    private val branchDao: BranchDao,
    private val businessLogic: BusinessLogic,
    private val addressLogic: AddressLogic,
) {

    fun insertOrReturnExisting(branch: Branch) =
        search(branch, Pageable.ofSize(1).withPage(0)).content.firstOrNull() ?: save(branch)

    fun search(branch: Branch, pageable: Pageable) =
        branchDao.findAll(Example.of(branch, ExampleMatcher.matching().withIncludeNullValues()), pageable)

    fun save(branch: Branch): Branch {
        val b = businessLogic.insertOrReturnExisting(branch.business!!)
        return branchDao.save(branch.copy(business = b))
    }

    @Throws(
        IllegalArgumentException::class, NoSuchElementException::class,
        OptimisticLockingFailureException::class
    )
    fun get(branch: Branch): Branch {
        if (branch.id != null) return branchDao.getReferenceById(branch.id!!)
        if (!branch.email.isNullOrBlank()) return branchDao.findByEmail(branch.email!!)
            .orElseThrow()
        if (!branch.phone.isNullOrBlank()) return branchDao.findByPhone(branch.phone!!)
            .orElseThrow()
        else throw IllegalArgumentException("Branch must have id, email or phone")
    }

    @Throws(
        IllegalArgumentException::class, NoSuchElementException::class,
        OptimisticLockingFailureException::class
    )
    fun get(businessId: UUID): List<Branch>? {
        return branchDao.findByBusinessId(businessId).orElseThrow()
    }

    @Throws(
        IllegalArgumentException::class, NoSuchElementException::class,
        OptimisticLockingFailureException::class
    )

    fun update(branch: Branch): Branch {
        if (branch.address == null) throw IllegalArgumentException("Address cannot be null")
        val addressId = addressLogic.insertOrReturnExisting(branch.address!!)
        return branchDao.save(branch.copy(address = Address().apply { id = addressId }))
    }

    @Throws(
        IllegalArgumentException::class, NoSuchElementException::class,
        OptimisticLockingFailureException::class
    )
    fun delete(id: String) {
        val branch = branchDao.getReferenceById(UUID.fromString(id))
        branchDao.save(branch.copy(active = false))
    }
}