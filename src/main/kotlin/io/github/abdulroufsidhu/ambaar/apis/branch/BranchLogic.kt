package io.github.abdulroufsidhu.ambaar.apis.branch

import io.github.abdulroufsidhu.ambaar.apis.address.AddressLogic
import io.github.abdulroufsidhu.ambaar.apis.business.Business
import io.github.abdulroufsidhu.ambaar.apis.business.BusinessLogic
import jakarta.transaction.Transactional
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BranchLogic(
    private val branchDao: BranchDao,
    private val businessLogic: BusinessLogic,
    private val addressLogic: AddressLogic,
) {

    @Throws(
        IllegalArgumentException::class, NoSuchElementException::class,
        OptimisticLockingFailureException::class
    )
    @Transactional
    fun create(branch: Branch): Branch {
        val bid = if (branch.business?.id == null) {
            businessLogic.create(
                branch.business ?: throw IllegalArgumentException("Business cannot be null")
            ).id
        } else branch.business?.id ?: throw IllegalArgumentException("Business cannot be null")

        if (branch.address == null) throw IllegalArgumentException("Address cannot be null")
        val addr = addressLogic.saveOrFind(branch.address!!)
        branch.address = addr
        val saved = branchDao.save(branch.copy(business = Business(id = bid)))
        println("branch created with data: $saved")
        return saved
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
        val branch = branchDao.getReferenceById(UUID.fromString(id))
        branchDao.save(branch.copy(active = false))
    }
}