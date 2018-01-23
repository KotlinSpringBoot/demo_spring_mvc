package com.easy.springboot.demo_spring_mvc.aop

import com.easy.springboot.demo_spring_mvc.constant.CommonContext
import com.easy.springboot.demo_spring_mvc.dao.UserDao
import com.easy.springboot.demo_spring_mvc.entity.User
import com.easy.springboot.demo_spring_mvc.exception.NoPermissionException
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


    lateinit var userDao: UserDao

    @Pointcut("execution(* com.easy.springboot.demo_spring_mvc.router.*.*(..))")
    fun userPermissionPointCut() {

    }

    @Before("userPermissionPointCut()")
    @Throws(Throwable::class)
    fun doBefore(joinPoint: JoinPoint) {
        // 接收到请求，记录请求内容
        val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val request = attributes.request
        val requestURL = request.requestURL.toString()
        if (requestURL.indexOf(CommonContext.USER_ADMIN_PERM) > 0) {
            val session = request.session
            val currentUser = session.getAttribute(CommonContext.CURRENT_USER_CONTEXT) as? User ?: throw NoPermissionException("没有权限")
            val roles = currentUser.roles
            roles.forEach {
                if (it.role == "ROLE_ADMIN") {
                    return
                }
            }
            forwardNoPermissionResponse(attributes)
        }
    }

    /**
     * 跳转没有权限页面
     */
    private fun forwardNoPermissionResponse(attributes: ServletRequestAttributes) {
        val request = attributes.request
        val response = attributes.response
        request.getRequestDispatcher("/error/403")
                .forward(request, response)
    }

    @AfterReturning(returning = "ret", pointcut = "userPermissionPointCut()")// returning的值和doAfterReturning的参数名一致
    @Throws(Throwable::class)
    fun doAfterReturning(ret: Any) {
        // 处理完请求，返回内容
        LOG.info("返回值:" + ret)
    }

    @Around("userPermissionPointCut()")
    @Throws(Throwable::class)
    fun doAround(pjp: ProceedingJoinPoint): Any {
        val startTime = System.currentTimeMillis()
        val ob = pjp.proceed()// ob 为方法的返回值
        LOG.info("耗时(ms):" + (System.currentTimeMillis() - startTime))
        return ob
    }


    @AfterThrowing(throwing = "ex", pointcut = "execution(public * com.easy.springboot.demo_spring_mvc.router.RouterController.*.*(..))")
    fun afterThrowing(ex: Throwable) {
    }

}