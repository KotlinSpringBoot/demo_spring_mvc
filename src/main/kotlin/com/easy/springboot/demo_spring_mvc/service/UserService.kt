
package com.easy.springboot.demo_spring_mvc.service

import com.easy.springboot.demo_spring_mvc.entity.User
import com.easy.springboot.demo_spring_mvc.result.LoginResult
import com.easy.springboot.demo_spring_mvc.result.RegisterResult
import org.springframework.validation.BindingResult

/**
     * Created by Kor on 2018-01-22 23:04:17. author: 东海陈光剑
     */
interface UserService {
    fun login(user: User): LoginResult<String>
    fun register(user: User,bindingResult: BindingResult): RegisterResult<String>
}
