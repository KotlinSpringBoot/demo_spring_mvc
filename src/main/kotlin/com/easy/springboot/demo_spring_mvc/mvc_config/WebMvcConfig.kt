package com.easy.springboot.demo_spring_mvc.mvc_config

import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.support.config.FastJsonConfig
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter
import com.easy.springboot.demo_spring_mvc.handler.LoginSessionHandlerInterceptor
import freemarker.template.Configuration
import freemarker.template.TemplateException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.format.FormatterRegistry
import org.springframework.format.datetime.DateFormatter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.*
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver
import java.io.IOException


@org.springframework.context.annotation.Configuration
open class WebMvcConfig : WebMvcConfigurationSupport() {
    val log = LoggerFactory.getLogger(WebMvcConfig::class.java)
    @Autowired lateinit var loginSessionHandlerInterceptor: LoginSessionHandlerInterceptor
    @Autowired lateinit var environment: Environment

    /**
     * 拦截器配置
     */
    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
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

    }

    /**
     * 静态资源路径映射
     */
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        super.addResourceHandlers(registry)
        registry.addResourceHandler("/app/**").addResourceLocations("classpath:/static/app/")
        registry.addResourceHandler("/bower_components/**").addResourceLocations("classpath:/static/bower_components/")

    }


//@GetMapping(value = ["/index"])
//fun index(): String {
//    return "index"
//}
//
//@GetMapping(value = ["/about"])
//fun about(): String {
//    return "about"
//}
//
//@GetMapping(value = ["/error/403"])
//fun error_403(): String {
//    return "error/403"
//}
//
//@GetMapping(value = ["/error/500"])
//fun error_500(): String {
//    return "error/500"
//}
    /**
     * View - Controller 映射配置
     */
override fun addViewControllers(registry: ViewControllerRegistry) {
    super.addViewControllers(registry)

    registry.addViewController("/").setViewName("/index")
    registry.addViewController("/index").setViewName("/index")
    registry.addViewController("/about").setViewName("/about")
    registry.addViewController("/error/403").setViewName("/error/403")
    registry.addViewController("/error/500").setViewName("/error/500")
}

/**
 * 重写 addCorsMappings方法:
addMapping：配置可以被跨域的路径。
allowedMethods：允许访问该跨域资源服务器的请求方法，如：POST、GET、PUT、DELETE等。
allowedOrigins：允许访问的跨域资源的请求域名。
allowedHeaders：允许请求 header 的访问，如："X-TOKEN"。
 */
override fun addCorsMappings(registry: CorsRegistry) {
    super.addCorsMappings(registry)
    registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("PUT,POST,GET,DELETE,OPTIONS")
            .allowedHeaders("*")
}


/**
 *
 * FreeMarker 视图解析器配置
 * 配置了@Bean注解，该注解会将方法返回值加入到Spring Ioc 容器内。
 * @return
 */
@Bean
open fun freeMarkerViewResolver(): FreeMarkerViewResolver {
    val viewResolver = FreeMarkerViewResolver()
    // freemarker本身配置了templateLoaderPath而在viewResolver中不需要配置prefix，且路径前缀必须配置在 templateLoaderPath 中
    viewResolver.setPrefix("")
    viewResolver.setSuffix(".ftl")
    viewResolver.isCache = false
    viewResolver.setContentType("text/html;charset=UTF-8")
    viewResolver.setRequestContextAttribute("requestContext") //为模板调用时，调用 request 对象的变量名
    viewResolver.order = 0
    viewResolver.setExposeRequestAttributes(true);
    viewResolver.setExposeSessionAttributes(true);
    return viewResolver
}


    /**
    spring.freemarker.suffix=.ftl
    spring.freemarker.templateEncoding=UTF-8
    spring.freemarker.templateLoaderPath=classpath:/templates/

     * 1.spring和freemarker的整合，需要定义两个bean：FreeMarkerViewResolver、FreeMarkerConfigurer。
     * 2.spring在Dispatcher中定义了视图渲染的过程:创建视图，然后利用Freemarker本身提供的Template方法来处理。
     */
@Bean
open fun freemarkerConfig(): FreeMarkerConfigurer {
    val freemarkerConfig = FreeMarkerConfigurer()
    freemarkerConfig.setDefaultEncoding("UTF-8")
    freemarkerConfig.setTemplateLoaderPath("classpath:/templates/")
    var configuration: Configuration? = null
    try {
        configuration = freemarkerConfig.createConfiguration()
        configuration.defaultEncoding = "UTF-8"
    } catch (e: IOException) {
        log.error("freemarker配置bean，IO异常: {}", e)
    } catch (e: TemplateException) {
        log.error("freemarker配置bean，TemplateException 异常: {}", e)
    }

    val freemarkerVars = mutableMapOf<String, Any>()
    freemarkerVars["rootContextPath"] = environment.getProperty("root.context.path")
    freemarkerConfig.setFreemarkerVariables(freemarkerVars)
    return freemarkerConfig
}

    /**
     * 配置视图解析器：ViewResolver
     * ########################################################
    ###FREEMARKER (FreeMarkerAutoConfiguration)
    ########################################################
    spring.freemarker.allow-request-override=false
    spring.freemarker.cache=true
    spring.freemarker.check-template-location=true
    spring.freemarker.charset=UTF-8
    spring.freemarker.content-type=text/html
    spring.freemarker.expose-request-attributes=false
    spring.freemarker.expose-session-attributes=false
    spring.freemarker.expose-spring-macro-helpers=false
    #spring.freemarker.prefix=
    #spring.freemarker.request-context-attribute=
    #spring.freemarker.settings.*=
    spring.freemarker.suffix=.ftl
    spring.freemarker.template-loader-path=classpath:/templates/
    #comma-separated list
    #spring.freemarker.view-names= # whitelist of view names that can be resolved
     */
override fun configureViewResolvers(registry: ViewResolverRegistry) {
    super.configureViewResolvers(registry)
    registry.viewResolver(freeMarkerViewResolver())
}

/**
 * 配置消息转换器
 */
override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
    super.configureMessageConverters(converters)
    //创建fastjson消息转换器: FastJsonHttpMessageConverter
    val fastConverter = FastJsonHttpMessageConverter()
    //创建 FastJsonConfig 配置类
    val fastJsonConfig = FastJsonConfig()
    //定制过滤 JSON 返回
    fastJsonConfig.setSerializerFeatures(
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullStringAsEmpty
    )
    fastConverter.setFastJsonConfig(fastJsonConfig)
    //将 fastConverter 添加到视图消息转换器列表内
    converters.add(fastConverter)
}

override fun addFormatters(registry: FormatterRegistry) {
    super.addFormatters(registry)
    registry.addFormatter(DateFormatter("yyyy-MM-dd"))
}


}