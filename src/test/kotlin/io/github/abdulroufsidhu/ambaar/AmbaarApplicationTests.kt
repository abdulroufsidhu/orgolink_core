package io.github.abdulroufsidhu.ambaar

import io.github.abdulroufsidhu.ambaar.address.Address
import io.github.abdulroufsidhu.ambaar.address.AddressQueries
import io.github.abdulroufsidhu.ambaar.user.User
import io.github.abdulroufsidhu.ambaar.user.UserQueries
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant

@SpringBootTest
@AutoConfigureMockMvc
class AmbaarApplicationTests(
    @Autowired private val userQueries: UserQueries,
    @Autowired private val addresQueries: AddressQueries,
) {
    @Test
    fun contextLoads() {
        val user = User(
            username = "abdulroufsidhu",
            password = "123456789",
            email = "abdulroufsidhu@gmail.com",
            null,
            Address(
                "123",
                "street",
                "city",
                "zip",
                Address.Country.PAKISTAN,
                null,
                null,
                null,
                Instant.now(),
                Instant.now()
            ),
            null,
            Instant.now(),
            Instant.now(),
        )

        val addresRepo = addresQueries.save(user.address)
        val userRepo = userQueries.save(user.copy(address = addresRepo))

        Assertions.assertEquals("abdulroufsidhu", userRepo.username)

    }

}
