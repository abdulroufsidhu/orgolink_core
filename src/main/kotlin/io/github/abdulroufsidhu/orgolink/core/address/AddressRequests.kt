package io.github.abdulroufsidhu.orgolink.core.address

import io.github.abdulroufsidhu.orgolink.core.config.Responser
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/api/address")
@CrossOrigin
class AddressRequests(private val addressLogic: io.github.abdulroufsidhu.orgolink.core.address.AddressLogic) {

    @GetMapping("")
    fun getAddresses(
        @RequestParam("page_size") pageSize: Int,
        @RequestParam("page") page: UInt,
        address: Address,
    ) = Responser.success {
        addressLogic.getAddresses(address, Pageable.ofSize(pageSize).withPage(page.toInt()))
    }

}