package io.pivotal.shinyay.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class IndexController {

    @RequestMapping("/", method = [RequestMethod.GET])
    fun index() = "index"
}