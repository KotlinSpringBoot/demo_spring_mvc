package com.easy.springboot.demo_spring_mvc.login

import com.easy.springboot.demo_spring_mvc.constant.CommonContext
import com.easy.springboot.demo_spring_mvc.entity.User
import com.easy.springboot.demo_spring_mvc.result.LoginResult
import com.easy.springboot.demo_spring_mvc.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpSession

/**
 * Created by Kor on 2018-01-22 23:04:17. author: 东海陈光剑
 */
@Controller
class LoginController {
    @Autowired lateinit var UserService: UserService

    @PostMapping(value = ["/doLogin"])
    @ResponseBody
    fun doLogin(user: User): LoginResult<String> {
        return UserService.login(user)
    }

    @GetMapping(value = ["/login"])
    fun login(model: Model): String {
        return "login"
    }

    @GetMapping(value = ["/logout"])
    fun logout(session: HttpSession): String {
        session.removeAttribute(CommonContext.CURRENT_USER_CONTEXT)
        return "forward:/"
    }

}
