package io.github.abdulroufsidhu.orgolink.core.address

import io.github.abdulroufsidhu.orgolink.core.config.Responser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/address")
@CrossOrigin
class AddressRequests(private val addressLogic: io.github.abdulroufsidhu.orgolink.core.address.AddressLogic) {

    @GetMapping("")
    fun getAddress(
        address: io.github.abdulroufsidhu.orgolink.core.address.Address,
    ) = Responser.success {
        addressLogic.findIncludingId(address)
    }

}