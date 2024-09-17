package io.github.abdulroufsidhu.orgolink.core.user

import io.github.abdulroufsidhu.orgolink.core.config.Responser
import io.github.abdulroufsidhu.orgolink.core.user.requests.SignInRequest
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/users")
@CrossOrigin
class UserRequests(
    private val userLogic: UserLogic,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @PutMapping("/sign-up")
    fun createUser(@RequestBody user: User) = Responser.success { userLogic.createUser(user) }

    @PostMapping("/login")
    fun signIn(
        @RequestBody signInRequest: SignInRequest,
    ) = Responser.success {
        val response = userLogic.signIn(signInRequest)
        response
    }

    @PatchMapping("/update-password")
    fun updatePassword(
        @AuthenticationPrincipal user: User,
        @RequestBody @Valid password: String
    ) = Responser.success {
            userLogic.updatePassword(user.id!!, password)
        }

    @PatchMapping("/update-user")
    fun updateUser(
        @AuthenticationPrincipal user: User,
        @RequestBody updates: User,
    ) {
        userLogic.updateUser(updates.apply { id = user.id }.copy(active = user.active))
    }
}