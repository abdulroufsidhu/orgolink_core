package io.github.abdulroufsidhu.ambaar.apis.user.data_models.request

import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class"
)
data class SignInResponse(
    val token: String?,
    val user: User?,
)