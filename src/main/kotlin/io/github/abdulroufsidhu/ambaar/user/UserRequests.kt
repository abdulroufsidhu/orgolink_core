package io.github.abdulroufsidhu.ambaar.user

import io.github.abdulroufsidhu.ambaar.core.Responser
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
    fun createUser(@Valid @RequestBody user: User): Responser<Responser.ResponseObj<User>> =
        Responser.success {
            userLogic.createUser(user)
        }

    data class SignInRequstData(
        var email: String,
        var password: String,
    )

    @PostMapping("/sign-in")
    fun signIn(
        @RequestBody signInRequstData: SignInRequstData,
    ): Responser<Responser.ResponseObj<User>> = Responser.success {
        userLogic.signIn(signInRequstData.email, signInRequstData.password)
    }

    @PatchMapping("/update-password/{id}")
    fun updatePassword(
        @PathVariable("id") userId: String,
        @Valid @RequestBody password: String
    ): Responser<Responser.ResponseObj<User>> =
        Responser.success {
            userLogic.updatePassword(userId, password)
        }

}