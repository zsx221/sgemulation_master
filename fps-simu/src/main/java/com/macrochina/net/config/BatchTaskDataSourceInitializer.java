package com.macrochina.net.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import javax.sql.DataSource;

@Configuration
public class BatchTaskDataSourceInitializer {
    /**
     * 构建Resource对象
     */
    @Value("classpath:opf.sql")
    private Resource businessScript;


    @Value("${spring.profiles.active}")
    private String envFlag;

    @Value("classpath:autoInsert.sql")
    private Resource autoInsert;


    FileSystemResource resource;
    /**
     * 自定义Bean实现业务的特殊需求
     *
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {

        final DataSourceInitializer initializer = new DataSourceInitializer();
        // 设置数据源
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }
    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        if (envFlag != null && envFlag.equals("opf")) {
            populator.addScripts(businessScript);
            resource = new FileSystemResource("/home/UploadsFile/autoInsert.sql");
            if (resource.exists()&& resource.isReadable())
                populator.addScripts(resource);
            else if (autoInsert.exists()){
                populator.addScripts(autoInsert);
            }
        }
        populator.addScripts(businessScript);
        return populator;
    }
}
