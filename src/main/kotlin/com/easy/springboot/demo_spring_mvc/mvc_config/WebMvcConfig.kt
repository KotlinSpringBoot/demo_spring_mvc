package com.easy.springboot.demo_spring_mvc.mvc_config

import com.easy.springboot.demo_spring_mvc.handler.LoginSessionHandlerInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@Configuration
class WebMvcConfig : WebMvcConfigurerAdapter() {
    @Autowired lateinit var loginSessionHandlerInterceptor: LoginSessionHandlerInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(loginSessionHandlerInterceptor).
                addPathPatterns("/**").
                excludePathPatterns(
                        "/index",
                        "/login",
                        "/doLogin",
                        "/logout",
                        "/register",
                        "/doRegister",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.css.map",
                        "/**/*.jpeg",
                        "/**/*.ico",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.woff",
                        "/**/*.woff2"
                )
        super.addInterceptors(registry)
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/app/**").addResourceLocations("classpath:/static/app/")
        registry.addResourceHandler("/bower_components/**").addResourceLocations("classpath:/static/bower_components/")
        super.addResourceHandlers(registry)
    }

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
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/index").setViewName("/index")
        registry.addViewController("/about").setViewName("/about")
        registry.addViewController("/error/403").setViewName("/error/403")
        registry.addViewController("/error/500").setViewName("/error/500")
        super.addViewControllers(registry)
    }

}