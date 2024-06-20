package io.github.abdulroufsidhu.ambaar.core

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import org.hibernate.annotations.ColumnDefault
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@MappedSuperclass
abstract class BaseTable(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    open var id: String?,
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonProperty("created_at", access = JsonProperty.Access.READ_ONLY)
    open var createdAt: Instant?,
    @JsonProperty("updated_at", access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    open var updatedAt: Instant?,
) {
    @PrePersist
    fun preInsert() {
        if (this.updatedAt==null) updatedAt = Instant.now()
        if (this.createdAt==null) createdAt = Instant.now()
    }
}