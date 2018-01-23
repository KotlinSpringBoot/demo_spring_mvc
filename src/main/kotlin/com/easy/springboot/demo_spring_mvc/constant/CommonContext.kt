package com.easy.springboot.demo_spring_mvc.constant

object CommonContext {
    val CURRENT_USER_CONTEXT = "currentUser"
    /**
     * 过滤器白名单：包含这些名单中的 url 不需要过滤，直接 Pass
     */
    val ESCAPE_URLS = arrayOf("/index", "/login", "/doLogin", "/logout", ".js", ".css", ".jpeg", ".ico", ".jpg", ".png", ".woff")
    val FILTERED_REQUEST = "@session_context_filtered_request@"
    val LOGIN_REDIRECT_URL = "login_redirect_url"

    /**
     *  USER_ADMIN 权限校验：匹配请求： /api/user，/user/list 等
     */
    val USER_ADMIN_PERM = "/user"
}
