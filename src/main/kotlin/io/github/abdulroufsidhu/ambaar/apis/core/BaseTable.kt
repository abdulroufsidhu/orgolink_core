package io.github.abdulroufsidhu.ambaar.apis.core

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import org.hibernate.annotations.ColumnDefault
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.UUID

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseTable(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    @field:Schema(hidden = true)
    open var id: UUID?,
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonProperty("created_at", access = JsonProperty.Access.READ_ONLY)
    @field:Schema(hidden = true)
    open var createdAt: Instant?,
    @JsonProperty("updated_at", access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    @field:Schema(hidden = true)
    open var updatedAt: Instant?,
    @CreatedBy
    @Column(name = "created_by", nullable = true, updatable = false)
    @JsonProperty("created_by", access = JsonProperty.Access.READ_ONLY)
    @field:Schema(hidden = true)
    var createdBy: UUID? = null,
    @LastModifiedBy
    @Column(name = "updated_by", nullable = true)
    @JsonProperty("updated_by", access = JsonProperty.Access.READ_ONLY)
    @field:Schema(hidden = true)
    var updatedBy: UUID? = null,
) {
    @PrePersist
    fun preInsert() {
        if (this.updatedAt==null) updatedAt = Instant.now()
        if (this.createdAt==null) createdAt = Instant.now()
    }
}