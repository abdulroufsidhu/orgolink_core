package io.github.abdulroufsidhu.orgolink.core.config

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.Parameter
import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
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
    @Parameter(hidden = true)
    open var id: UUID? = null,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP(6)")
    @JsonProperty("created_at", access = JsonProperty.Access.READ_ONLY,)
    @Parameter(hidden = true)

    open var createdAt: Instant = Instant.now(),
    @JsonProperty("updated_at", access = JsonProperty.Access.READ_ONLY,)
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP(6)")
    open var updatedAt: Instant = Instant.now(),

    @Parameter(hidden = true)
    @CreatedBy
    @Column(name = "created_by", nullable = true, updatable = false)
    @JsonProperty("created_by", access = JsonProperty.Access.READ_ONLY)

    @Parameter(hidden = true)
    open var createdBy: UUID? = null,
    @LastModifiedBy
    @Column(name = "updated_by", nullable = true)
    @JsonProperty("updated_by", access = JsonProperty.Access.READ_ONLY)

    @Parameter(hidden = true)
    open var updatedBy: UUID? = null,
)