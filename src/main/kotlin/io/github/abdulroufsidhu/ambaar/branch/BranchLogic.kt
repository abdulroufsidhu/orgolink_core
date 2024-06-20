package io.github.abdulroufsidhu.ambaar.branch

import io.github.abdulroufsidhu.ambaar.address.AddressLogic
import jakarta.transaction.Transactional
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Service

@Service
class BranchLogic(
    private val branchDao: BranchDao,
    private val addressLogic: AddressLogic,
) {

    @Throws(
        IllegalArgumentException::class, NoSuchElementException::class,
        OptimisticLockingFailureException::class
    )
    @Transactional
    fun create(branch: Branch): Branch {
        if (branch.address == null) throw IllegalArgumentException("Address cannot be null")
        val addr = addressLogic.saveOrFind(branch.address!!)
        branch.address = addr
        return branchDao.save(branch)
    }

    @Throws(
        IllegalArgumentException::class, NoSuchElementException::class,
        OptimisticLockingFailureException::class
    )
    fun get(branch: Branch): Branch {
        if (!branch.id.isNullOrBlank()) return branchDao.getReferenceById(branch.id!!)
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
    fun get(businessId: String): List<Branch>? {
        return branchDao.findByBusinessId(businessId).orElseThrow()
    }

    @Throws(
        IllegalArgumentException::class, NoSuchElementException::class,
        OptimisticLockingFailureException::class
    )
    @Transactional
    fun update(branch: Branch): Branch {
        if (branch.address == null) throw IllegalArgumentException("Address cannot be null")
        val addr = addressLogic.saveOrFind(branch.address!!)
        branch.address = addr
        return branchDao.save(branch)
    }

    @Throws(
        IllegalArgumentException::class, NoSuchElementException::class,
        OptimisticLockingFailureException::class
    )
    fun delete(id: String) {
        val branch = branchDao.getReferenceById(id)
        branchDao.save(branch.copy(active = false))
    }
}