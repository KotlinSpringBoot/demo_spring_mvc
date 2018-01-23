
package com.easy.springboot.demo_spring_mvc.entity
import javax.persistence.*
import java.util.Date
/**
     * Created by Kor on 2018-01-23 19:21:32. author: 东海陈光剑
     */
@Entity
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1
    var gmtCreate = Date()
    var gmtModify = Date()
    var isDeleted = 0
    @Column(unique = true)
    var role = "ROLE_USER"
}
