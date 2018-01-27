package com.easy.springboot.demo_spring_mvc

import com.easy.springboot.demo_spring_mvc.dao.CategoryDao
import com.easy.springboot.demo_spring_mvc.dao.RoleDao
import com.easy.springboot.demo_spring_mvc.dao.UserDao
import com.easy.springboot.demo_spring_mvc.entity.Category
import com.easy.springboot.demo_spring_mvc.entity.Role
import com.easy.springboot.demo_spring_mvc.entity.User
import com.easy.springboot.demo_spring_mvc.util.MD5Util
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.context.support.beans
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import java.io.File

@SpringBootApplication
@ServletComponentScan(basePackages = ["com.easy.springboot.demo_spring_mvc.filter"])
@EnableAutoConfiguration(exclude = [ErrorMvcAutoConfiguration::class])
@EnableWebMvc
class DemoSpringMvcApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder().initializers(
            beans {
                bean {
                    ApplicationRunner {
                        initUser()
                        initCategory()
                    }
                }
            }
    ).sources(DemoSpringMvcApplication::class.java).run(*args)
}

private fun BeanDefinitionDsl.BeanDefinitionContext.initUser() {
    val userDao = ref<UserDao>()
    val roleDao = ref<RoleDao>()
    try {
        /*********** 角色 ***********/
        // 普通用户角色
        val roleUser = Role()
        roleUser.role = "ROLE_USER"
        // 超级管理员角色
        val roleAdmin = Role()
        roleAdmin.role = "ROLE_ADMIN"
        val r1 = roleDao.save(roleUser)
        val r2 = roleDao.save(roleAdmin)
        /*********** 用户 ***********/
        // 普通用户
        val user = User()
        user.username = "user"
        user.password = MD5Util.md5("user")

        val userRoles = setOf(r1)
        user.roles = userRoles
        userDao.save(user)

        val jack = User()
        jack.username = "jack"
        jack.password = MD5Util.md5("123456")

        val jackRoles = setOf(r1, r2)
        jack.roles = jackRoles
        userDao.save(jack)

        // 超级管理员用户
        val admin = User()
        admin.username = "admin"
        admin.password = MD5Util.md5("admin")

        val adminRoles = setOf(r1, r2)
        admin.roles = adminRoles
        userDao.save(admin)
    } catch (e: Exception) {
    }
}

private fun BeanDefinitionDsl.BeanDefinitionContext.initCategory() {
    /** 初始化学科分类数据 */
    val CategoryDao = ref<CategoryDao>()
    println(File(".").absolutePath) // /Users/jack/easykotlin/reakt/.
    val f1 = File("src/main/resources/学科分类.data")
    f1.readLines().forEach {
        try {
            val items = it.split("=")
            println("${items[0]}=${items[1]}")
            val category = Category()
            category.code = items[0]
            category.name = items[1]
            category.detail = items[2]
            category.type = 1
            CategoryDao.save(category)
        } catch (e: Exception) {
        }
    }

    /** 初始化图书分类数据 */

    println(File(".").absolutePath) // /Users/jack/easykotlin/reakt/.
    val f2 = File("src/main/resources/图书分类.data")
    f2.readLines().forEach {
        try {
            val items = it.split("=")
            println("${items[0]}=${items[1]}")
            val category = Category()
            category.code = items[0]
            category.name = items[1]
            category.type = 2
            CategoryDao.save(category)
        } catch (e: Exception) {
        }
    }

    /** 初始化全球行业分类数据 */
    println(File(".").absolutePath) // /Users/jack/easykotlin/reakt/.
    val f3 = File("src/main/resources/全球行业分类.data")
    f3.readLines().forEach {
        try {
            val items = it.split("=")
            println("${items[0]}=${items[1]}")
            val category = Category()
            category.code = items[0]
            category.name = items[1]
            category.type = 3
            CategoryDao.save(category)
        } catch (e: Exception) {
        }
    }
    /** 初始化中国产业分页数据 */
    println(File(".").absolutePath) // /Users/jack/easykotlin/reakt/.
    val f4 = File("src/main/resources/中国产业分页.data")
    f4.readLines().forEach {
        try {
            val items = it.split("=")
            println("${items[0]}=${items[1]}")
            val category = Category()
            category.code = items[0]
            category.name = items[1]
            category.detail = items[2]
            category.type = 4
            CategoryDao.save(category)
        } catch (e: Exception) {
        }
    }

    /** 初始化中国产业分页数据 */
    println(File(".").absolutePath) // /Users/jack/easykotlin/reakt/.
    val f5 = File("src/main/resources/编程语言.data")
    f5.readLines().forEach {
        try {
            val items = it.split("=")
            println("${items[0]}=${items[1]}=${items[2]}")
            val category = Category()
            category.code = items[0]
            category.name = items[1]
            category.detail = items[2]
            category.type = 5
            CategoryDao.save(category)
        } catch (e: Exception) {
        }
    }
}

