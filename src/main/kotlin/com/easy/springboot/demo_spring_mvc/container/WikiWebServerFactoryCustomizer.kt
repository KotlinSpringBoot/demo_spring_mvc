package com.easy.springboot.demo_spring_mvc.container

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.nio.charset.Charset
import java.time.Duration


@Component
class WikiWebServerFactoryCustomizer : WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    @Autowired lateinit var environment: Environment

    override fun customize(server: TomcatServletWebServerFactory) {
        server.port = 9000
        server.contextPath = environment.getProperty("root.context.path")
        server.uriEncoding = Charset.forName("UTF-8")
        server.sessionTimeout = Duration.ofMinutes(10L)
    }
}