package com.easy.springboot.demo_spring_mvc.entity

import java.util.*
import javax.persistence.*

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
    var username = ""
    var password = ""
}
