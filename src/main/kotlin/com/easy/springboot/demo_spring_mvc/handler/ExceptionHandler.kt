package com.easy.springboot.demo_spring_mvc.handler

import com.easy.springboot.demo_spring_mvc.exception.NoPermissionException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(value = Exception::class)
    fun defaultErrorHandler(req: HttpServletRequest, e: Exception): ModelAndView {
        val mav = ModelAndView()
        mav.addObject("stackTrace", e.stackTrace)
        mav.addObject("errorMessage", e.message)
        mav.addObject("url", req.requestURL)
        mav.viewName = "forward:/error/500"
        return mav
    }

}
