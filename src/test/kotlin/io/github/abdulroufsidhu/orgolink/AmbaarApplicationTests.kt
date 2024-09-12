package io.github.abdulroufsidhu.orgolink

import io.github.abdulroufsidhu.orgolink.core.address.AddressDao
import io.github.abdulroufsidhu.orgolink.core.user.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
class OrgolinkCoreApplicationTests(
    @Autowired private val userDao: UserDao,
    @Autowired private val addresDao: AddressDao,
) {


}
