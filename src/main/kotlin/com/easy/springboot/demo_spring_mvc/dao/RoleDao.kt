
package com.easy.springboot.demo_spring_mvc.dao
import com.easy.springboot.demo_spring_mvc.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
/**
     * Created by Kor on 2018-01-23 19:21:32. author: 东海陈光剑
     */
interface RoleDao : JpaRepository<Role, Long> {
}
