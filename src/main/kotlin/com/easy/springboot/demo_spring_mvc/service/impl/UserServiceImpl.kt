package com.easy.springboot.demo_spring_mvc.service.impl

import com.easy.springboot.demo_spring_mvc.constant.CommonContext
import com.easy.springboot.demo_spring_mvc.dao.UserDao
import com.easy.springboot.demo_spring_mvc.entity.User
import com.easy.springboot.demo_spring_mvc.result.LoginResult
import com.easy.springboot.demo_spring_mvc.result.RegisterResult
import com.easy.springboot.demo_spring_mvc.service.UserService
import com.easy.springboot.demo_spring_mvc.util.MD5Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.validation.BindingResult
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpSession


/**
 * Created by Kor on 2018-01-22 23:04:17. author: 东海陈光剑
 */
@Service
class UserServiceImpl : UserService {
    @Autowired lateinit var UserDao: UserDao

    override fun register(user: User, bindingResult: BindingResult): RegisterResult<String> {
        val RegisterResult = RegisterResult<String>()
        val sb = StringBuffer()
        if (bindingResult.hasErrors()) {
            bindingResult.fieldErrors.forEach {
                sb.append(it.defaultMessage).append(",")
            }
            RegisterResult.isSuccess = false
            RegisterResult.msg = sb.toString()
            return RegisterResult
        }

        val username = user.username
        val u = UserDao.findByUsername(username)
        if (u != null) {
            RegisterResult.isSuccess = false
            RegisterResult.msg = "用户名已存在"
            return RegisterResult
        }
        val password = user.password
        user.password = MD5Util.md5(password)
        UserDao.save(user)
        RegisterResult.isSuccess = true
        RegisterResult.msg = "注册成功"
        return RegisterResult
    }

    override fun login(user: User): LoginResult<String> {
        val loginResult = LoginResult<String>()
        // 根据用户名查询 User 对象
        val u = UserDao.findByUsername(user.username)
        val password = MD5Util.md5(user.password)

        if (u != null && u.password != password) {// 1.用户存在&&密码错误
            loginResult.isSuccess = false
            loginResult.msg = "登录失败：密码错误"
        } else if (u != null && u.password == password) {// 2.用户存在&&密码正确
            // 3.将用户对象 User 放到 Session 中
            // 通过 RequestContextHolder 获取当前请求属性
            val requestAttributes = RequestContextHolder.currentRequestAttributes()
            val request = (requestAttributes as ServletRequestAttributes).request
            val response = requestAttributes.response
            val session = request.session
            setSessionUser(u, session)
            // 4.是否有登陆之后的跳转 URL
            var toURL = session.getAttribute(CommonContext.LOGIN_REDIRECT_URL) as? String
            if (toURL == null) {
                toURL = "/"
            }
            // 5.返回登陆成功
            loginResult.isSuccess = true
            loginResult.msg = "登录成功"
            loginResult.result = null
            loginResult.redirectUrl = toURL
        } else {
            // 5.直接返回用户登录失败
            loginResult.isSuccess = false
            loginResult.msg = "登录失败：用户不存在"
            loginResult.result = null
        }
        return loginResult
    }

    private fun setSessionUser(u: User, session: HttpSession) {
        session.setAttribute(CommonContext.CURRENT_USER_CONTEXT, u)
    }

}

