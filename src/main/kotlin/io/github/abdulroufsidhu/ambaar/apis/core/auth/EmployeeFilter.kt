package io.github.abdulroufsidhu.ambaar.apis.core.auth

import io.github.abdulroufsidhu.ambaar.apis.employee.EmployeeLogic
import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.NoSuchElementException

@Component
class EmployeeFilter(
    private val employeeLogic: EmployeeLogic
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val empId = request.getHeader("x-emp")
        if (empId.isNullOrBlank()) {
            filterChain.doFilter(request, response)
            return
        }
        try {
            request.setAttribute("employee", employeeLogic.get(empId).orElseThrow())
        } catch (e: Exception) {
            logger.error(e)
        }
        filterChain.doFilter(request, response)
    }
}