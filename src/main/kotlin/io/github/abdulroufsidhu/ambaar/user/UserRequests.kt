package io.github.abdulroufsidhu.ambaar.user

import io.github.abdulroufsidhu.ambaar.core.Responser
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/users")
class UserRequests(
    private val userQueries: UserQueries,
) {
    @PostMapping("/create")
    fun createUser(@Valid @RequestBody user: User) = Responser.success {
        userQueries.save(user)
    }

    @GetMapping("/sign-in")
    fun signIn(
        @RequestParam("email") email: String,
        @RequestParam("password") password: String
    ) = Responser.success {
        userQueries.findByEmailAndPassword(email, password)
    }

    @PatchMapping("/update")
    fun updateUser(@Valid @RequestBody user: User) = Responser.success {
        userQueries.save(user)
    }

}