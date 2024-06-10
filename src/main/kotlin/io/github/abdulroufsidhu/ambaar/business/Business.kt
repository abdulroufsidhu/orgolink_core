package io.github.abdulroufsidhu.ambaar.business

import io.github.abdulroufsidhu.ambaar.branch.Branch
import io.github.abdulroufsidhu.ambaar.core.BaseTable
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.Instant

@Entity
@Table(name = "businesses")
data class Business(
    @Column(unique = true)
    @field:NotNull
    @field:NotBlank
    var name: String? = null,
    var description: String? = null,
    @Column(name = "license_number", unique = true, nullable = false)
    @field:NotNull
    @field:NotBlank
    var licence: String? = null,

    @OneToMany(mappedBy = "business", cascade = [CascadeType.ALL])
    var branches: List<Branch>? = null,
    override var id: String? = null,
    override var createdAt: Instant? = null,
    override var updatedAt: Instant? = null,
) : BaseTable(id, createdAt, updatedAt)
