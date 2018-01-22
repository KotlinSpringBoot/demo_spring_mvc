package com.easy.springboot.demo_spring_mvc.service.impl

import com.easy.springboot.demo_spring_mvc.constant.CommonContext
import com.easy.springboot.demo_spring_mvc.dao.UserDao
import com.easy.springboot.demo_spring_mvc.entity.User
import com.easy.springboot.demo_spring_mvc.result.LoginResult
import com.easy.springboot.demo_spring_mvc.service.UserService
import com.easy.springboot.demo_spring_mvc.util.MD5Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpSession


/**
 * Created by Kor on 2018-01-22 23:04:17. author: 东海陈光剑
 */
@Service
class UserServiceImpl : UserService {
    @Autowired lateinit var UserDao: UserDao

    override fun login(user: User): LoginResult<String> {
        val loginResult = LoginResult<String>()
        val u = UserDao.findByUsername(user.username)
        val password = MD5Util.md5(user.password)
        // 通过 RequestContextHolder 获取当前请求属性
        val requestAttributes = RequestContextHolder.currentRequestAttributes()
        val request = (requestAttributes as ServletRequestAttributes).request
        val response = requestAttributes.response
        val session = request.session
        if (u != null && u.password == password) {
            setSessionUser(u, session)
            var toURL = session.getAttribute(CommonContext.LOGIN_REDIRECT_URL) as? String
            if (toURL == null) {
                toURL = "/"
            }
            loginResult.isSuccess = true
            loginResult.msg = "登录成功"
            loginResult.result = null
            loginResult.redirectUrl = toURL
        } else {
            loginResult.isSuccess = false
            loginResult.msg = "登录失败"
            loginResult.result = null
        }
        return loginResult
    }

    private fun setSessionUser(u: User, session: HttpSession) {
        session.setAttribute(CommonContext.CURRENT_USER_CONTEXT, u)
    }

}

