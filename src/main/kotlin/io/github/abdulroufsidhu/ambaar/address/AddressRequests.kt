package io.github.abdulroufsidhu.ambaar.address

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/address")
class AddressRequests(private val addressLogic: AddressLogic) {

    @GetMapping("")
    fun getAddress(
        @RequestParam("id") id: String?,
        @RequestParam("state") state: String?,
        @RequestParam("city") city: String?,
        @RequestParam("zip") zip: String?,
        @RequestParam("country") country: Address.Country?,
    ) = addressLogic.find(Address(state = state, city = city, zip = zip, country = country))

}