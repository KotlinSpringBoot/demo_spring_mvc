package com.easy.springboot.demo_spring_mvc.login

import com.easy.springboot.demo_spring_mvc.entity.User
import com.easy.springboot.demo_spring_mvc.result.RegisterResult
import com.easy.springboot.demo_spring_mvc.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.validation.Valid

/**
 * Created by Kor on 2018-01-22 23:04:17. author: 东海陈光剑
 */
@Controller
class RegisterController {
    @Autowired lateinit var UserService: UserService

    @PostMapping(value = ["/api/doRegister"])
    @ResponseBody
    fun doRegister(@Valid user: User, bindingResult: BindingResult): RegisterResult<String> {
        return UserService.register(user, bindingResult)
    }

    @GetMapping(value = ["/register"])
    fun register(): String {
        return "register"
    }

}
