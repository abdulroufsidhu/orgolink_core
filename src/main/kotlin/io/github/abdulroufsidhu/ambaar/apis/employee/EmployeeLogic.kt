package io.github.abdulroufsidhu.ambaar.apis.employee

import io.github.abdulroufsidhu.ambaar.apis.branch.Branch
import io.github.abdulroufsidhu.ambaar.apis.branch.BranchLogic
import io.github.abdulroufsidhu.ambaar.apis.core.caching.HibernateInitializer
import io.github.abdulroufsidhu.ambaar.apis.user.UserLogic
import jakarta.persistence.EntityNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeLogic(
    private val employeeDao: EmployeeDao,
    private val branchLogic: BranchLogic,
    private val userLogic: UserLogic,
) {

    private val logger = LoggerFactory.getLogger(this::class.java)


    @Throws(
        IllegalArgumentException::class, OptimisticLockingFailureException::class
    )
    fun create(employee: Employee): Employee {
//        Logger.getLogger(EmployeeLogic::class.java.name).info("Creating Employee: $employee")
        logger.info("creating employee: $employee")
        val userId = userLogic.insertOrReturnExisting(employee.user)
        logger.info("found user: $userId")
        assert(userId != null)
        val bid = branchLogic.insertOrReturnExisting(employee.branch)
            ?: throw IllegalStateException("unable to create or find branch")
        logger.info("found branch: $bid")
        return employeeDao.save(
            employee.copy(
                user = employee.user.copy(id = UUID.fromString(userId)),
                branch = Branch(id = UUID.fromString(bid))
            )
        )
    }


    @Throws(
        IllegalArgumentException::class, OptimisticLockingFailureException::class
    )
    fun update(employee: Employee): Employee {
        if (employee.id == null) throw IllegalArgumentException("id must not be null")
        return employeeDao.save(employee)
    }


    @Throws(
        IllegalArgumentException::class, OptimisticLockingFailureException::class
    )
    fun delete(id: String): Employee {
        val emp = employeeDao.getReferenceById(UUID.fromString(id))
        return employeeDao.save(emp.copy(active = false))
    }


    @Cacheable(value = ["employee"], key = "{#branchId, #userId}")

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
        NoSuchElementException::class,
    )
    fun read(branchId: String?, userId: String?): List<Employee> {
        return if (!branchId.isNullOrBlank()) employeeDao.findByBranchId(UUID.fromString(branchId))
            .orElse(listOf())
        else employeeDao.findByUserId(
            UUID.fromString(
                userId ?: throw IllegalArgumentException("userId must not be null")
            )
        ).orElse(listOf())
    }


    @Cacheable(value = ["employee"], key = "#userId")

    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
        NoSuchElementException::class,
    )
    fun read(userId: UUID?): List<Employee> {
        return employeeDao.findByUserId(
            userId ?: throw IllegalArgumentException("userId must not be null")
        ).orElse(listOf())
    }


    @Cacheable(value = ["employee"], key = "#employeeId")
    @Throws(EntityNotFoundException::class)
    fun get(employeeId: String): Employee? {
        val emp = employeeDao.findByEmployeeId(UUID.fromString(employeeId)).orElseThrow()
        HibernateInitializer.initialize(emp)
        return emp
    }

}