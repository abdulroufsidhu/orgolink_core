package io.github.abdulroufsidhu.ambaar

import io.github.abdulroufsidhu.ambaar.address.Address
import io.github.abdulroufsidhu.ambaar.address.AddressDao
import io.github.abdulroufsidhu.ambaar.user.data_models.User
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

    }

}
