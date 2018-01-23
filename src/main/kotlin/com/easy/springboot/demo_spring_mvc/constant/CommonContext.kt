package com.easy.springboot.demo_spring_mvc.constant

object CommonContext {
    /**
     * Session 中的当前登录用户的 Key
     */
    val CURRENT_USER_CONTEXT = "currentUser"
    /**
     * 过滤器白名单：包含这些名单中的 url 不需要过滤，直接 Pass
     */
    val FILTER_PASS_URLS = arrayOf(
        "/index", "/login", "/doLogin", "/logout",
        "/register", "/doRegister",
        ".js", ".css", ".jpeg", ".ico", ".jpg", ".png", ".woff")
    /**
     * 过滤器是否执行过滤 Flag 在 Session 中的 Key
     */
    val FILTERED_REQUEST = "@session_context_filtered_request@"
    /**
     * 登录完成之后重定向到原来的 URL 在 Session 中的 Key
     */
    val LOGIN_REDIRECT_URL = "login_redirect_url"

    /**
     *  USER_ADMIN 权限校验：匹配请求： /api/user，/user/list 等
     */
    val USER_ADMIN_PERM = "/user"
}
