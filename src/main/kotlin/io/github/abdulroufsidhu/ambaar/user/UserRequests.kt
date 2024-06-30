package io.github.abdulroufsidhu.ambaar.user

import io.github.abdulroufsidhu.ambaar.core.ResponseObj
import io.github.abdulroufsidhu.ambaar.core.Responser
import io.github.abdulroufsidhu.ambaar.user.data_models.SignInRequest
import io.github.abdulroufsidhu.ambaar.user.data_models.User
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
@CrossOrigin
class UserRequests(
    private val userLogic: UserLogic,
) {
    @PutMapping("/create")
    fun createUser(@Valid @RequestBody user: User): Responser<ResponseObj<User>> =
        Responser.success {
            userLogic.createUser(user)
        }

    @PostMapping("/sign-in")
    fun signIn(
        @RequestBody signInRequest: SignInRequest,
    ) = Responser.success {
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