package com.easy.springboot.demo_spring_mvc.router

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * Created by Kor on 2017-12-24 14:04:20.
 */
@Controller
class RouterController {

    @GetMapping(value = ["/index"])
    fun index(): String {
        return "index"
    }

    @GetMapping(value = ["/about"])
    fun about(): String {
        return "about"
    }

    @GetMapping(value = ["/error/403"])
    fun error_403(): String {
        return "error/403"
    }

    @GetMapping(value = ["/category/list/{type}"])
    fun category(@PathVariable(value = "type") type: Int, model: Model): String {
        model["type"] = type
        return "category/list"
    }

    @GetMapping(value = ["/user/list"])
    fun user_list(): String {
        return "user/list"
    }

}
