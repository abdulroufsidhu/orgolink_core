package io.github.abdulroufsidhu.ambaar.apis.core.caching

import io.github.abdulroufsidhu.ambaar.apis.branch.Branch
import io.github.abdulroufsidhu.ambaar.apis.employee.Employee
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User
import io.github.abdulroufsidhu.ambaar.apis.user.person.Person
import org.hibernate.Hibernate
import org.springframework.stereotype.Component

@Component
class HibernateInitializer {
    fun initialize(user: User) {
        if (Hibernate.isInitialized(user).not()) Hibernate.initialize(user)
        if (Hibernate.isInitialized(user.authorities).not()) Hibernate.initialize(user.authorities)
        initialize(user.person)
    }
    fun initialize(person: Person) {
        if (Hibernate.isInitialized(person).not()) Hibernate.initialize(person)
        if (Hibernate.isInitialized(person.address).not()) Hibernate.initialize(person.address)
    }
    fun initialize(branch: Branch) {
        if (Hibernate.isInitialized(branch).not()) Hibernate.initialize(branch)
        if (Hibernate.isInitialized(branch.address).not()) Hibernate.initialize(branch.address)
        if (Hibernate.isInitialized(branch.business).not()) Hibernate.initialize(branch.business)
    }
    fun initialize(employee: Employee) {
        initialize(employee.user)
        initialize(employee.branch)
        if (Hibernate.isInitialized(employee.permissions).not()) Hibernate.initialize(employee.permissions)
    }
}