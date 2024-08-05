package io.github.abdulroufsidhu.ambaar.pages

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping


@Controller
@Profile("ui")
class PagesController {

    fun getPage(pageContent: String, model: Model): String {
        val content = when (pageContent) {
            "auth" -> "auth"
            "dashboard" -> "dashboard"
            else -> "default"
        }
        model.addAttribute("content", content)
        return "base_page.html"
    }

    @GetMapping("/index", "/", "/auth")
    fun index(model: Model) = getPage("auth", model)

    @GetMapping("/dashboard")
    fun dashboard(model: Model) = getPage("dashboard", model)
}