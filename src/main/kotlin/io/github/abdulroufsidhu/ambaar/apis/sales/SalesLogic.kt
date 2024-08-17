package io.github.abdulroufsidhu.ambaar.apis.sales

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SalesLogic(
    private val salesDao: SalesDao,
) {

    fun save(sale: Sale) {
        salesDao.save(sale.copy(id = null))
    }

    fun update(sale: Sale) {
        assert(sale.id != null)
        salesDao.save(sale)
    }

    fun getSales(branchId: String, page: Int, size: Int) {
        salesDao.findByBranchId(UUID.fromString(branchId), Pageable.ofSize(size).withPage(page))
    }

    fun delete(id: String) {
        salesDao.softDelete(UUID.fromString(id), true)
    }

}