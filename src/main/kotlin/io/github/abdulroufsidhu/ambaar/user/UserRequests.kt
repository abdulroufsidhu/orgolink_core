package io.github.abdulroufsidhu.ambaar.user

import io.github.abdulroufsidhu.ambaar.core.Responser
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart

@Controller
@RequestMapping("/users")
class UserRequests(
    private val userLogic: UserLogic,
) {
    @PostMapping("/create")
    fun createUser(@Valid @RequestBody user: User) = Responser.success {
        userLogic.createUser(user)
    }

    @GetMapping("/sign-in")
    fun signIn(
        @RequestParam("email") email: String,
        @RequestParam("password") password: String
    ) = Responser.success {
        userLogic.signIn(email, password)
    }

    @PatchMapping("/update-password/{id}")
    fun updatePassword(@PathVariable("id") userId: String, @Valid @RequestPart password: String) =
        Responser.success {
            userLogic.updatePassword(userId, password)
        }

}