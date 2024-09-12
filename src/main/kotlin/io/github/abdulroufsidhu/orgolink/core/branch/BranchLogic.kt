package io.github.abdulroufsidhu.orgolink.core.branch

import io.github.abdulroufsidhu.orgolink.core.address.Address
import io.github.abdulroufsidhu.orgolink.core.address.AddressLogic
import io.github.abdulroufsidhu.orgolink.core.business.BusinessLogic
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BranchLogic(
    private val branchDao: io.github.abdulroufsidhu.orgolink.core.branch.BranchDao,
    private val businessLogic: BusinessLogic,
    private val addressLogic: AddressLogic,
    private val jdbcTemplate: JdbcTemplate,
) {

    fun insertOrReturnExisting(branch: Branch): String? {
        assert(branch.business != null)
        val businessId = businessLogic.insertOrReturnExisting(branch.business!!)
            ?: throw IllegalStateException("unable to create or retrieve business")

        assert(branch.address != null)
        val addressId = addressLogic.insertOrReturnExisting(branch.address!!)
        val sql = """
            INSERT INTO branches (
                id
                , code
                , description
                , email
                , name
                , phone
                , website
                , address_id
                , business_id
            ) VALUES (
                '${branch.id ?: UUID.randomUUID()}'
                , '${branch.code}'
                , '${branch.description}'
                , '${branch.email}'
                , '${branch.name}'
                , '${branch.phone}'
                , '${branch.website}'
                , '${addressId}'
                , '${businessId}'
            ) ON CONFLICT DO NOTHING RETURNING id
        """.trimIndent()
        return jdbcTemplate.queryForObject(sql, String::class.java)
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
        return branchDao.save(branch.copy(address = Address().apply { id = UUID.fromString(addressId) }))
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