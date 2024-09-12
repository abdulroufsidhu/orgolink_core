package io.github.abdulroufsidhu.orgolink.core.user

import io.github.abdulroufsidhu.orgolink.core.config.Responser
import io.github.abdulroufsidhu.orgolink.core.user.requests.SignInRequest
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/users")
@CrossOrigin
class UserRequests(
    private val userLogic: UserLogic,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @PutMapping("/sign-up")
    fun createUser(@ModelAttribute user: User) =
        Responser.success {
            userLogic.createUser(user)
        }

    @PostMapping("/login")
    fun signIn(
        @ModelAttribute signInRequest: SignInRequest,
    ) = Responser.success {
        val response = userLogic.signIn(signInRequest)
        response
    }

    @PatchMapping("/update-password/{id}")
    fun updatePassword(
        @PathVariable("id") userId: String,
        @Valid password: String
    ) =
        Responser.success {
            userLogic.updatePassword(userId, password)
        }

}