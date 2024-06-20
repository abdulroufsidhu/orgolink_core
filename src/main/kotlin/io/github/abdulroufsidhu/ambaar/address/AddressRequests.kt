package io.github.abdulroufsidhu.ambaar.address

import io.github.abdulroufsidhu.ambaar.core.Responser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/address")
class AddressRequests(private val addressLogic: AddressLogic) {

    @GetMapping("")
    fun getAddress(
        address: Address,
    ) = Responser.success {
        addressLogic.findIncludingId(address)
    }

}