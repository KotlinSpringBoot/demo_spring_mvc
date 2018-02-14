package com.easy.springboot.demo_spring_mvc.handler

import org.slf4j.LoggerFactory
import org.springframework.util.ClassUtils
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

/**
 * 优先级高于 WikiHandlerExceptionResolver
 */
@ControllerAdvice
class WikiExceptionHandler {
    var log = LoggerFactory.getLogger(WikiExceptionHandler::class.java)

    @ExceptionHandler(value = ArithmeticException::class)
    fun defaultErrorHandler(req: HttpServletRequest, e: Exception): ModelAndView {
        log.error("WikiExceptionHandler ===> {}", e.message)
        e.printStackTrace()
        //这里可根据不同异常引起类做不同处理方式
        val exceptionName = ClassUtils.getShortName(e.javaClass)
        log.info("WikiExceptionHandler ===> {}", exceptionName)
        val mav = ModelAndView()
        mav.addObject("stackTrace", e.stackTrace)
        mav.addObject("errorMessage", e.message)
        mav.addObject("url", req.requestURL)
        mav.viewName = "forward:/error/500"
        return mav
    }

}
