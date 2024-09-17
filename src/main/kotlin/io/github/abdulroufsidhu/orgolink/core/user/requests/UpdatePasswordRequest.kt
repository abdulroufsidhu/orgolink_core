package io.github.abdulroufsidhu.orgolink.core.user.requests

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdatePasswordRequest(
    @JsonProperty("old_password")
    var oldPassword: String,
    @JsonProperty("new_password")
    var newPassword: String,
)
