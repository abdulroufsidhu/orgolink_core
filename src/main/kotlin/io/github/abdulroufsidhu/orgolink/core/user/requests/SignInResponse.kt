package io.github.abdulroufsidhu.orgolink.core.user.requests

import io.github.abdulroufsidhu.orgolink.core.user.User

data class SignInResponse(
    val token: String?,
    val user: User?,
)