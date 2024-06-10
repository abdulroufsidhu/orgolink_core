package io.github.abdulroufsidhu.ambaar.employee

import jakarta.transaction.Transactional
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Service

@Service
class EmployeeLogic(
    private val employeeDao: EmployeeDao,
) {

    @Transactional
    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class
    )
    fun create(employee: Employee): Employee {
        return employeeDao.save(employee)
    }

    @Transactional
    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class
    )
    fun update(employee: Employee): Employee {
        if (employee.id == null) throw IllegalArgumentException("id must not be null")
        return employeeDao.save(employee)
    }

    @Transactional
    @Throws(
        IllegalArgumentException::class,
        OptimisticLockingFailureException::class
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
    fun read(branchId: String): List<Employee> {
        return employeeDao.findByBranchId(branchId).orElseThrow()
    }

}