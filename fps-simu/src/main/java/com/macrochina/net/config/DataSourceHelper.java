package com.macrochina.net.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/*@PropertySource("classpath:application-opf.yml")
@Configuration*/
public class DataSourceHelper  {

    private Logger log = LoggerFactory.getLogger(DataSourceHelper.class);

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;




    @PostConstruct
    public void init() {
        try {
           /* Optional<String> hostopt =Optional.ofNullable(env.getProperty("spring.dataSource.driver-class-name")) ;
            driver=hostopt.orElse("");
            System.out.println("----------------------");
            System.out.println("driver====="+driver+hostopt);*/
            System.out.println("----------------------"+url);
            Class.forName(driver);
            URI uri = new URI(url.replace("jdbc:", ""));
            String host = uri.getHost();
            int port = uri.getPort();
            String path = uri.getPath();
            Connection connection = DriverManager.getConnection(url.replace(path,""), username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS `" + path.replace("/", "") + "` DEFAULT CHARACTER SET = `utf8` COLLATE `utf8_general_ci`;");
            statement.close();
            connection.close();
        } catch (URISyntaxException | ClassNotFoundException | SQLException e) {
            log.error(e.getMessage());
        }
    }

}
