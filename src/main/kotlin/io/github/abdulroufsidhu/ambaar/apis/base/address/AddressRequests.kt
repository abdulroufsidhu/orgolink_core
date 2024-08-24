package io.github.abdulroufsidhu.ambaar.apis.base.address

import io.github.abdulroufsidhu.ambaar.apis.base.core.Responser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/api/address")
@CrossOrigin
class AddressRequests(private val addressLogic: AddressLogic) {

    @GetMapping("")
    fun getAddress(
        address: Address,
    ) = Responser.success {
        addressLogic.findIncludingId(address)
    }

}