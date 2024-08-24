package io.github.abdulroufsidhu.ambaar.apis.base.employee

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.github.abdulroufsidhu.ambaar.apis.base.branch.Branch
import io.github.abdulroufsidhu.ambaar.apis.base.core.BaseTable
import io.github.abdulroufsidhu.ambaar.apis.base.user.data_models.User
import jakarta.persistence.*
import java.time.Instant
import java.util.UUID

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class"
)
@Entity
@Table(
    name = "employees",
    uniqueConstraints = [
        UniqueConstraint(name = "unique_employee", columnNames = ["user_id", "branch_id"])
    ]
)
data class Employee(

    var designation: String?,

    @ManyToOne(targetEntity = User::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne(targetEntity = Branch::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    var branch: Branch,

    @ElementCollection(targetClass = Permissions::class, fetch = FetchType.EAGER)
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
        VIEW_SALES,
        GENERATE_SALES,
        UPDATE_SALES,
        DELETE_SALES,
    }

    override fun toString(): String {
        return "Employee(designation=$designation, branch=$branch, permissions=$permissions, active=$active, id=$id, createdAt=$createdAt, updatedAt=$updatedAt)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Employee

        if (designation != other.designation) return false
        if (user != other.user) return false
        if (branch != other.branch) return false
        if (permissions != other.permissions) return false
        if (active != other.active) return false
        if (id != other.id) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = designation?.hashCode() ?: 0
        result = 31 * result + user.hashCode()
        result = 31 * result + branch.hashCode()
        result = 31 * result + permissions.hashCode()
        result = 31 * result + active.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        return result
    }
}