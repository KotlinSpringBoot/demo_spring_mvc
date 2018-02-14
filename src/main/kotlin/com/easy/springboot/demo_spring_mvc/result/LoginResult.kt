package com.easy.springboot.demo_spring_mvc.result

class LoginResult<T> {
    var result: T? = null
    var isSuccess: Boolean = false
    var msg: String = ""
    var redirectUrl: String = ""
}
