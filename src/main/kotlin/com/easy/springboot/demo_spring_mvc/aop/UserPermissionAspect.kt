package com.easy.springboot.demo_spring_mvc.aop

import com.easy.springboot.demo_spring_mvc.constant.CommonContext
import com.easy.springboot.demo_spring_mvc.entity.User
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


@Component
@Aspect
class UserPermissionAspect {
    private val LOG = LoggerFactory.getLogger(UserPermissionAspect::class.java)

    @Pointcut("execution(* com.easy.springboot.demo_spring_mvc.controller.*.*(..))")
    fun userPermissionPointCut() {

    }

    @Before("userPermissionPointCut()")
    @Throws(Throwable::class)
    fun doBefore(joinPoint: JoinPoint) {
        val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val request = attributes.request
        val requestURL = request.requestURL.toString()
        if (requestURL.indexOf(CommonContext.USER_ADMIN_PERM) > 0) {
            val session = request.session
            val currentUser = session.getAttribute(CommonContext.CURRENT_USER_CONTEXT) as? User
            if (currentUser != null) {
                val roles = currentUser.roles
                roles.forEach {
                    if (it.role == "ROLE_ADMIN") {
                        return
                    }
                }
            }
            processNoPermissionResponse(attributes)
        }
    }

    private fun processNoPermissionResponse(attributes: ServletRequestAttributes) {
        // 跳转没有权限页面
        forwardNoPermissionResponse(attributes)
    }

//    private fun writeResponseJsonNoPermission(attributes: ServletRequestAttributes) {
//        val response = attributes.response
//        response.characterEncoding = "UTF-8"
//        response.contentType = "application/json; charset=utf-8"
//        val writer = response.writer
//        val result = HashMap<String, Any>()
//        result.put("code", "403")
//        result.put("message", "无权限")
//        writer.write(ObjectMapper().writeValueAsString(result))
//    }

    private fun forwardNoPermissionResponse(attributes: ServletRequestAttributes) {
        val request = attributes.request
        val response = attributes.response
        request.getRequestDispatcher("/error/403")
                .forward(request, response)
    }

//    private fun isAjax(attributes: ServletRequestAttributes): Boolean {
//        val request = attributes.request
//        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"), ignoreCase = true)
//    }


    @AfterReturning(returning = "ret", pointcut = "userPermissionPointCut()")// returning的值和doAfterReturning的参数名一致
    @Throws(Throwable::class)
    fun doAfterReturning(ret: Any) {
        LOG.info("返回值:" + ret)
    }

    @Around("userPermissionPointCut()")
    @Throws(Throwable::class)
    fun doAround(pjp: ProceedingJoinPoint): Any {
        val startTime = System.currentTimeMillis()
        val ob = pjp.proceed() // 执行方法：proceed()， ob 为方法的返回值
        LOG.info("耗时(ms):" + (System.currentTimeMillis() - startTime))
        return ob
    }
}