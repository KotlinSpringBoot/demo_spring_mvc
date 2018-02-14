package com.easy.springboot.demo_spring_mvc.filter

import com.easy.springboot.demo_spring_mvc.constant.CommonContext
import com.easy.springboot.demo_spring_mvc.entity.User
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(urlPatterns = ["/*"])
class AuthenticationOncePerRequestFilter : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        println("===> AuthenticationFilter doFilter")
        val requestURL = request.requestURL.toString()
        // 1.判断是否需要鉴权
        if (CommonContext.isEscapeUrls(requestURL)) {
            println("Pass RequestURL: ${requestURL}")
            filterChain.doFilter(request, response)
        } else {
            // 2.执行用户登陆状态鉴权
            System.err.println("Auth RequestURL: ${requestURL}")
            doAuthenticationFilter(request, response, filterChain)
            filterChain.doFilter(request, response)
        }
    }



    private fun doAuthenticationFilter(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val sessionUser = getSessionUser(request)

        // 如果当前 session 中不存在该用户（ 用户未登录 ）
        if (sessionUser == null) {
            // 4.跳转登陆页面
            redirectLogin(request, response)
        }
    }

    private fun redirectLogin(request: ServletRequest, response: ServletResponse) {
        val httpServletRequest = request as HttpServletRequest
        var toURL = httpServletRequest.requestURL.toString()
        // 查询参数处理
        val queryString = httpServletRequest.queryString
        if (queryString!=null && queryString != "") {
            toURL += "?$queryString"
        }
        // 将用户请求的 URL 存入 Session 中，用于登陆成功之后跳转
        httpServletRequest.session.setAttribute(CommonContext.LOGIN_REDIRECT_URL, toURL)
        httpServletRequest.getRequestDispatcher("/login")
                .forward(request, response)
    }


    /**
     * 获取当前 Session 中是否有该用户
     */
    private fun getSessionUser(httpServletRequest: HttpServletRequest): User? {
        return httpServletRequest.session.getAttribute(CommonContext.CURRENT_USER_CONTEXT) as? User
    }
}
