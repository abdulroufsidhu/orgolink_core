package io.github.abdulroufsidhu.ambaar.employee

import io.github.abdulroufsidhu.ambaar.user.UserDao
import io.github.abdulroufsidhu.ambaar.user.UserLogic
import jakarta.transaction.Transactional
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Service

@Service
class EmployeeLogic(
    private val employeeDao: EmployeeDao,
    private val userDao: UserDao,
    private val userLogic: UserLogic,
) {

    @Transactional
    @Throws(
        IllegalArgumentException::class, OptimisticLockingFailureException::class
    )
    fun create(employee: Employee): Employee {
        var foundUser =
            userDao.findByEmail(employee.user.username)
                .orElse(null)
        if (foundUser == null) {
            foundUser = userLogic.createUser(employee.user)
        }
        return employeeDao.save(employee.copy(user = foundUser))
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
        val emp = employeeDao.getReferenceById(id)
        return employeeDao.save(emp.copy(active = false))
    }

    @Transactional
    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class,
        NoSuchElementException::class,
    )
    fun read(branchId: String?, userId: String?): List<Employee> {
        return if (!branchId.isNullOrBlank()) employeeDao.findByBranchId(branchId).orElseThrow()
        else employeeDao.findByUserId(
            userId ?: throw IllegalArgumentException("userId must not be null")
        ).orElseThrow()
    }

}