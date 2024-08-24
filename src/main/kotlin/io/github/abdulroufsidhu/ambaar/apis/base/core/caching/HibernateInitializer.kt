package io.github.abdulroufsidhu.ambaar.apis.base.core.caching

import io.github.abdulroufsidhu.ambaar.apis.base.branch.Branch
import io.github.abdulroufsidhu.ambaar.apis.base.employee.Employee
import io.github.abdulroufsidhu.ambaar.apis.base.user.data_models.User
import io.github.abdulroufsidhu.ambaar.apis.base.user.person.Person
import org.hibernate.Hibernate


object HibernateInitializer {
    fun initialize(user: User) {
        initialize(user.person)
    }

    fun initialize(person: Person) {
        initializeIfAlreadyNot(person.address)
    }

    fun initialize(branch: Branch) {
        initializeIfAlreadyNot(branch.address, branch.business)
    }

    fun initialize(employee: Employee) {
        initializeIfAlreadyNot(employee, employee.permissions, employee.user, employee.branch)
        initialize(employee.user)
        initialize(employee.branch)
    }

    fun initializeIfAlreadyNot(vararg proxies: Any?) {
        proxies.filterNotNull().forEach { proxy ->
            if (Hibernate.isInitialized(proxy).not()) Hibernate.initialize(proxy)
        }
    }
}