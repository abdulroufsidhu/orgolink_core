package io.github.abdulroufsidhu.ambaar.business

import io.github.abdulroufsidhu.ambaar.branch.Branch
import io.github.abdulroufsidhu.ambaar.core.BaseTable
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table( name = "businesses" )
data class Business(
    @Column(unique = true)
    var name: String,
    var description: String,
    @Column(name = "license_number", unique = true, nullable = false)
    var license: String,

    @OneToMany(mappedBy = "business", cascade = [CascadeType.ALL])
    var branches: List<Branch>,
    override var id: String? = null,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
) : BaseTable(id, createdAt, updatedAt)
