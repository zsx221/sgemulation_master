package com.macrochina.net;

import com.macrochina.net.service.SysParamsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
@EnableAsync//开启异步调用
@SpringBootApplication(
/*        exclude = {
        DataSourceAutoConfiguration.class
}*/
)
public class FpsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FpsApplication.class, args);
        SysParamsService.SysParamsServiceInit();
    }



    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        //factory.setPort(9000);
        Session session = new Session();
        Duration time=Duration.ofSeconds(36000);
        session.setTimeout(time);
        factory.setSession(session);
        //factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
        return factory;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
