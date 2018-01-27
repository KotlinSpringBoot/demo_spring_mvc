package com.easy.springboot.demo_spring_mvc.controller

import com.easy.springboot.demo_spring_mvc.dao.RoleDao
import com.easy.springboot.demo_spring_mvc.entity.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 * Created by Kor on 2018-01-23 19:21:32. author: 东海陈光剑
 */
@RestController
@RequestMapping("/api/role")
class ApiRoleController {
    @Autowired lateinit var RoleDao: RoleDao
    @GetMapping(value = ["/"])
    fun role(request: HttpServletRequest): List<Role> {
        return RoleDao.findAll()
    }

    @GetMapping(value = ["/{id}"])
    fun getOne(@PathVariable("id") id: Long): Role {
        return RoleDao.getOne(id)
    }
}
