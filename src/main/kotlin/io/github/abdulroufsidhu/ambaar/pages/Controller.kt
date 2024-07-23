package io.github.abdulroufsidhu.ambaar.pages

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PagesController {
    @GetMapping("/index")
    fun index() = "index.html"
}