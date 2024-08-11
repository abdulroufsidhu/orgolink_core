package io.github.abdulroufsidhu.ambaar.apis.employee

import io.github.abdulroufsidhu.ambaar.apis.branch.Branch
import io.github.abdulroufsidhu.ambaar.apis.branch.BranchLogic
import io.github.abdulroufsidhu.ambaar.apis.user.UserDao
import io.github.abdulroufsidhu.ambaar.apis.user.UserLogic
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EmployeeLogic(
    private val employeeDao: EmployeeDao,
    private val branchLogic: BranchLogic,
    private val userDao: UserDao,
    private val userLogic: UserLogic,
) {

    @Transactional
    @Throws(
        IllegalArgumentException::class, OptimisticLockingFailureException::class
    )
    fun create(employee: Employee): Employee {
//        Logger.getLogger(EmployeeLogic::class.java.name).info("Creating Employee: $employee")
        var foundUser =
            userDao.findByEmail(employee.user.username)
                .orElse(null)
        if (foundUser == null) {
            foundUser = userLogic.createUser(employee.user).user
                ?: throw IllegalArgumentException("user cannot be null")
        }
        val bid = if (employee.branch.id == null) {
            branchLogic.create(employee.branch).id
        } else employee.branch.id
        return employeeDao.save(employee.copy(user = foundUser, branch = Branch(id = bid)))
    }

    @Transactional
    @Throws(
        IllegalArgumentException::class, OptimisticLockingFailureException::class
    )
    fun update(employee: Employee): Employee {
        if (employee.id == null) throw IllegalArgumentException("id must not be null")
        return employeeDao.save(employee)
    }

    @Transactional
    @Throws(
        IllegalArgumentException::class, OptimisticLockingFailureException::class
    )
    fun delete(id: String): Employee {
        val emp = employeeDao.getReferenceById(UUID.fromString(id))
        return employeeDao.save(emp.copy(active = false))
    }

    @Transactional
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

    @Transactional
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

    @Throws(EntityNotFoundException::class)
    fun get(employeeId: String) = employeeDao.findByEmployeeId(UUID.fromString(employeeId))

}