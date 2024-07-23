package io.github.abdulroufsidhu.ambaar.apis.core.auth

import io.github.abdulroufsidhu.ambaar.apis.employee.Employee

object PermissionVerifier {

    @Throws(PermissionNotGrantedException::class)
    fun verify(employee: Employee, permission: Employee.Permissions): Boolean =
        employee.permissions.contains(permission)
            .ifFalse { throw PermissionNotGrantedException("Permission Not Granted") }

    @Throws(Throwable::class)
    fun Boolean.ifTrue(invoke: () -> Boolean): Boolean = ifCondition(true, invoke)


    @Throws(Throwable::class)
    fun Boolean.ifFalse(invoke: () -> Boolean): Boolean = ifCondition(false, invoke)

    @Throws(Throwable::class)
    fun Boolean.ifCondition(condition: Boolean, invoke: () -> Boolean): Boolean =
        if (this == condition) invoke()
        else this

    class PermissionNotGrantedException(msg: String) : Exception(msg)

}