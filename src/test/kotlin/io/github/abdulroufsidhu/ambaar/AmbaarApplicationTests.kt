package io.github.abdulroufsidhu.ambaar

import io.github.abdulroufsidhu.ambaar.address.Address
import io.github.abdulroufsidhu.ambaar.address.AddressDao
import io.github.abdulroufsidhu.ambaar.user.User
import io.github.abdulroufsidhu.ambaar.user.UserDao
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant

@SpringBootTest
@AutoConfigureMockMvc
class AmbaarApplicationTests(
    @Autowired private val userDao: UserDao,
    @Autowired private val addresDao: AddressDao,
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

        val addresRepo = addresDao.save(user.address)
        val userRepo = userDao.save(user.copy(address = addresRepo))

        Assertions.assertEquals("abdulroufsidhu", userRepo.username)

    }

}
