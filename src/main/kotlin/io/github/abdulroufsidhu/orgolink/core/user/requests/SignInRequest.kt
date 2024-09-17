package io.github.abdulroufsidhu.orgolink.core.user.requests


data class SignInRequest(
    var email: String,
    var password: String,
)