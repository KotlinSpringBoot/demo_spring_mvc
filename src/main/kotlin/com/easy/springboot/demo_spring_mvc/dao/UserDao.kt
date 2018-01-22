package com.easy.springboot.demo_spring_mvc.dao

import com.easy.springboot.demo_spring_mvc.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Created by Kor on 2018-01-22 23:04:17. author: 东海陈光剑
 */
interface UserDao : JpaRepository<User, Long> {
    @Query("select u from #{#entityName} u where u.username = :username")
    fun findByUsername(@Param("username") username: String): User?
}
