package io.github.abdulroufsidhu.ambaar

import io.github.abdulroufsidhu.ambaar.user.User
import io.github.abdulroufsidhu.ambaar.user.UserQueries
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
class AmbaarApplicationTests(
    @Autowired private val userQueries: UserQueries
) {
    @Test
    fun contextLoads() {
        val user = User(
            username = "abdulroufsidhu",
            password = "123456789",
            email = "abdulroufsidhu@gmail.com"
        )

        val userRepo = userQueries.save(user)

        Assertions.assertEquals("abdulroufsidhu", userRepo.username)

    }

}
