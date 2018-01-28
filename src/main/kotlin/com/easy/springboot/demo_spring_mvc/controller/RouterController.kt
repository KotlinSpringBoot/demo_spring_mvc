package com.easy.springboot.demo_spring_mvc.controller

import com.easy.springboot.demo_spring_mvc.exception.NoPermissionException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * Created by Kor on 2017-12-24 14:04:20.
 */
@Controller
class RouterController {

//    @GetMapping(value = ["/index"])
//    fun index(): String {
//        return "index"
//    }
//
//    @GetMapping(value = ["/about"])
//    fun about(): String {
//        return "about"
//    }
//
//    @GetMapping(value = ["/error/403"])
//    fun error_403(): String {
//        return "error/403"
//    }
//
//    @GetMapping(value = ["/error/500"])
//    fun error_500(): String {
//        return "error/500"
//    }

    /**
     * 测试全局异常处理：
     * @ControllerAdvice
     * class WikiExceptionHandler
     */
    @GetMapping(value = ["/test/ArithmeticException"])
    fun testArithmeticException(): String {
        val x = 1 / 0 // ArithmeticException
        return "exception"
    }

    /**
     * 测试异常处理
     * @Component
     * class WikiHandlerExceptionResolver : HandlerExceptionResolver
     */
    @GetMapping(value = ["/test/NoPermissionException"])
    fun testNoPermissionException(): String {
        throw NoPermissionException("没有权限")
    }

    @GetMapping(value = ["/category/list/{type}"])
    fun category(@PathVariable(value = "type") type: Int, model: Model): String {
        model["type"] = type
        return "category/list"
    }

    @GetMapping(value = ["/user/list"])
    fun user_list(): String {
        return "user/list"
    }

}
