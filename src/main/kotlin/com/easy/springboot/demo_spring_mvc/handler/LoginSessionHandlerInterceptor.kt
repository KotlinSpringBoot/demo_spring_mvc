package com.easy.springboot.demo_spring_mvc.handler

import com.easy.springboot.demo_spring_mvc.constant.CommonContext
import com.easy.springboot.demo_spring_mvc.entity.User
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * HandlerInterceptor拦截器的最终调用实现是在DispatcherServlet的doDispatch方法中，
 * 并且SpringMVC提供了HandlerExecutionChain来帮助我们执行所有配置的HandlerInterceptor拦截器，
 * 并分别调用HandlerInterceptor所提供的方法。
 */

@Component
class LoginSessionHandlerInterceptor : HandlerInterceptor {
    @Autowired lateinit var environment: Environment

    var log = LoggerFactory.getLogger(LoginSessionHandlerInterceptor::class.java)


    /**
     * 进入controller层之前拦截请求
     *
     * preHandle: 在执行controller处理之前执行，返回值为boolean ,返回值为true时接着执行postHandle和afterCompletion，如果我们返回false则中断执行
     *
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun preHandle(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, o: Any): Boolean {
        log.info("---------------------开始进入 LoginSessionHandlerInterceptor 拦截----------------------------")
        val requestURL = httpServletRequest.requestURL.toString()
        if (CommonContext.isEscapeUrls(requestURL)) {
            return true
        }
        val session = httpServletRequest.session
        val currentUser = session.getAttribute(CommonContext.CURRENT_USER_CONTEXT) as? User
        log.info("currentUser ===> ${ObjectMapper().writeValueAsString(currentUser)}")
        if (!StringUtils.isEmpty(currentUser)) {
            return true
        } else {
            val writer = httpServletResponse.writer
            val map = mutableMapOf<String, Any>()
            map["code"] = 403
            map["msg"] = "Request Invalid"
            writer.write(ObjectMapper().writeValueAsString(map))
            return false
        }
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在 modelAndView 中加入数据，比如当前时间;
     *
     * mappedHandler.applyPostHandle(processedRequest, response, mv)：最终会调用HandlerInterceptor的postHandle方法
     * 具体实现是在HandlerExecutionChain中实现如下，就是获取所有的拦截器并调用其postHandle方法。
     */
    @Throws(Exception::class)
    override fun postHandle(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, o: Any, modelAndView: ModelAndView?) {
        log.info("--------------  postHandle: 在执行 Controller的处理后，在处理 ModelAndView 前执行 ---------------")
        log.info("httpServletRequest => {}", httpServletRequest)
        log.info("httpServletResponse => {}", httpServletResponse)
        log.info("o => {}", o)
        log.info("modelAndView => {}", modelAndView)
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的 afterCompletion()
     */
    @Throws(Exception::class)
    override fun afterCompletion(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, o: Any, e: Exception?) {
        log.info("--------------- afterCompletion : 在 DispatchServlet 执行处理完 ModelAndView 后执行 -------------------------")
        if (e != null) {
            e.printStackTrace()
            log.info("afterCompletion Exception ===> {}", e.message)
        }
    }

}