package io.github.abdulroufsidhu.ambaar

import io.github.abdulroufsidhu.ambaar.apis.address.AddressDao
import io.github.abdulroufsidhu.ambaar.apis.user.UserDao
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
class AmbaarApplicationTests(
    @Autowired private val userDao: UserDao,
    @Autowired private val addresDao: AddressDao,
) {


}
