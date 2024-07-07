package io.github.abdulroufsidhu.ambaar.employee

import io.github.abdulroufsidhu.ambaar.branch.Branch
import io.github.abdulroufsidhu.ambaar.core.BaseTable
import io.github.abdulroufsidhu.ambaar.user.data_models.User
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.Instant
import java.util.UUID

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

    @Column(name = "id", nullable = false, updatable = false)
    override var id: UUID? = null,
    @Column(name = "created_at", nullable = false, updatable = false)
    override var createdAt: Instant? = null,
    @Column(name = "updated_at", nullable = false)
    override var updatedAt: Instant? = null,
) : BaseTable(id, createdAt, updatedAt) {
    enum class Permissions {
        BRANCH_CREATE,
        BRANCH_READ_ALL,
        BRANCH_UPDATE,
        BRANCH_DELETE,
        EMPLOYEE_CREATE,
        EMPLOYEE_READ_ALL,
        EMPLOYEE_UPDATE,
        EMPLOYEE_DELETE,
        INVENTORY_CREATE,
        INVENTORY_READ,
        INVENTORY_UPDATE,
        INVENTORY_DELETE,
    }
}