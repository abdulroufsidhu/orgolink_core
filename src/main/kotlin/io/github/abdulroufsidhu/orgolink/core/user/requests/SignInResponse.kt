package io.github.abdulroufsidhu.orgolink.core.user.requests

import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.github.abdulroufsidhu.orgolink.core.user.User

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class"
)
data class SignInResponse(
    val token: String?,
    val user: User?,
)