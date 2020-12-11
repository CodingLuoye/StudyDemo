package com.study;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
/**
 * @author YCKJ1409
 */
@SpringBootApplication(scanBasePackages = {"com.study"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ImportResource("classpath*:spring*.xml")
@MapperScan("mapper")
@ServletComponentScan
public class WeixinWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args){

        SpringApplication.run(WeixinWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WeixinWebApplication.class);
    }



}
