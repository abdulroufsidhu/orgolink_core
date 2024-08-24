package io.github.abdulroufsidhu.ambaar.apis.base.user.data_models.request

import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class"
)
data class SignInRequest(
    var email: String,
    var password: String,
)