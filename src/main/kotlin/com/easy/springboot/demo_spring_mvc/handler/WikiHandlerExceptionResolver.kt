package com.easy.springboot.demo_spring_mvc.handler

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.ClassUtils
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView
import java.lang.reflect.UndeclaredThrowableException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class WikiHandlerExceptionResolver : HandlerExceptionResolver {
    var log = LoggerFactory.getLogger(WikiHandlerExceptionResolver::class.java)
    override fun resolveException(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, o: Any, e: Exception): ModelAndView? {
        var ex: Exception = Exception()
        // Spring AOP UndeclaredThrowableException: Spring 的 aop 如果没有正常结束，经过检查点时就会抛出该异常,获取真实异常可以使用
        if (e is UndeclaredThrowableException) {
            ex = e.undeclaredThrowable as Exception
        }
        //这里可根据不同异常引起类做不同处理方式
        val exceptionName = ClassUtils.getShortName(ex::class.java)
        if ("NoPermissionException" == exceptionName) {
            log.info("WikiHandlerExceptionResolver NoPermissionException ===> {}", exceptionName)
            e.printStackTrace()
            //向前台返回错误信息
            val model = HashMap<String, Any?>()
            model.put("stackTrace", e.stackTrace)
            model.put("errorMessage", e.message)
            model.put("url", httpServletRequest.requestURL)
            return ModelAndView("forward:/error/403", model)
        }
        return null
    }
}