package com.easy.springboot.demo_spring_mvc.entity

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

/**
 * Created by Kor on 2018-01-22 23:04:17. author: 东海陈光剑
 */
@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1
    var gmtCreate = Date()
    var gmtModify = Date()
    var isDeleted = 0
    @Column(unique = true)
    @NotEmpty(message = "用户名不能为空")
    @Size(max = 100, min = 1, message = "用户名长度在1-20之间")
    var username = ""
    @NotEmpty(message = "密码不能为空")
    @Size(max = 100, min = 10, message = "密码长度在10-100之间")
    var password = ""
    @ManyToMany(targetEntity = Role::class, fetch = FetchType.EAGER)
    lateinit var roles: Set<Role>
}
