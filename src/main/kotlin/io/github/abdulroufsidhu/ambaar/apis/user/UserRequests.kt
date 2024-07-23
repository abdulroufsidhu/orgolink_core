package io.github.abdulroufsidhu.ambaar.apis.user

import io.github.abdulroufsidhu.ambaar.apis.core.ResponseObj
import io.github.abdulroufsidhu.ambaar.apis.core.Responser
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.SignInRequest
import io.github.abdulroufsidhu.ambaar.apis.user.data_models.User
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
@CrossOrigin
class UserRequests(
    private val userLogic: UserLogic,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    @PutMapping("/create")
    fun createUser(@ModelAttribute @RequestBody user: User): Responser<ResponseObj<User>> =
        Responser.success {
            logger.info("incomming user to create is: $user")
            userLogic.createUser(user)
        }

    @PostMapping("/sign-in")
    fun signIn(
        @ModelAttribute @RequestBody signInRequest: SignInRequest,
    ) = Responser.success {
        logger.info("signInRequest is: $signInRequest")
        userLogic.signIn(signInRequest)
    }

    @PatchMapping("/update-password/{id}")
    fun updatePassword(
        @PathVariable("id") userId: String,
        @Valid @RequestBody password: String
    ): Responser<ResponseObj<User>> =
        Responser.success {
            userLogic.updatePassword(userId, password)
        }

}