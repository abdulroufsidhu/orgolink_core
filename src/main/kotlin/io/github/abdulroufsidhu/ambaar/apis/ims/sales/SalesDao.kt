package io.github.abdulroufsidhu.ambaar.apis.ims.sales;

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface SalesDao : JpaRepository<Sale, UUID> {

    @Query("select s from Sale s where s.inventory.branch.id = ?1 and s.deleted = false")
    fun findByBranchId(id: UUID, pageable: Pageable): Page<Sale>

    @Modifying
    @Query("update Sale s set s.deleted = ?2 where s.id = ?1")
    fun softDelete(id: UUID, delete: Boolean = true)

}