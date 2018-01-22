package com.easy.springboot.demo_spring_mvc.controller

import com.easy.springboot.demo_spring_mvc.dao.UserDao
import com.easy.springboot.demo_spring_mvc.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 * Created by Kor on 2018-01-22 23:04:17. author: 东海陈光剑
 */
@RestController
@RequestMapping("/user")
class UserController {
    @Autowired lateinit var UserDao: UserDao

    @GetMapping(value = ["/"])
    fun user(request: HttpServletRequest): List<User> {
        return UserDao.findAll()
    }

    @GetMapping(value = ["/{id}"])
    fun getOne(@PathVariable("id") id: Long): User {
        return UserDao.getOne(id)
    }

}
