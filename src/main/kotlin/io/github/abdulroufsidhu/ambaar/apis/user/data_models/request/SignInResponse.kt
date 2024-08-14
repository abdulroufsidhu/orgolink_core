package io.github.abdulroufsidhu.ambaar.apis.user.data_models.request

import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User

data class SignInResponse(
    val token: String?,
    val user: User?,
)