package com.study.cmb.request;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * @author YCKJ1409
 * 配置RestTemplate
 */
public class TemplateConfig {

    public TemplateConfig() {
        System.out.println("构造RestTemplate======");
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        System.out.println("构造RestTemplate======");
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000);
        return factory;
    }
}

