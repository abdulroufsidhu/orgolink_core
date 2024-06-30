package io.github.abdulroufsidhu.ambaar.employee

import io.github.abdulroufsidhu.ambaar.branch.Branch
import io.github.abdulroufsidhu.ambaar.core.BaseTable
import io.github.abdulroufsidhu.ambaar.user.data_models.User
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.Instant

@Entity
@Table(
    name = "employees",
    uniqueConstraints = [
        UniqueConstraint(name = "unique_employee", columnNames = ["user_id", "branch_id"])
    ]
)
data class Employee(

    var designation: String?,

    @ManyToOne(targetEntity = User::class)
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne(targetEntity = Branch::class)
    @JoinColumn(name = "branch_id")
    var branch: Branch,

    @ElementCollection(targetClass = Permissions::class)
    @Enumerated(EnumType.STRING)
    var permissions: List<Permissions>,

    var active: Boolean = true,

    override var id: String? = null,
    override var createdAt: Instant? = null,
    override var updatedAt: Instant? = null,
) : BaseTable(id, createdAt, updatedAt) {
    enum class Permissions {
        CREATE_BUSINESS,
        READ_BUSINESS,
        UPDATE_BUSINESS,
        DELETE_BUSINESS,
        CREATE_BRANCH,
        READ_BRANCH,
        UPDATE_BRANCH,
        DELETE_BRANCH,
        CREATE_EMPLOYEE,
        READ_EMPLOYEE,
        UPDATE_EMPLOYEE,
        DELETE_EMPLOYEE,
        CREATE_INVENTORY,
        READ_INVENTORY,
        UPDATE_INVENTORY,
        DELETE_INVENTORY,
    }
}