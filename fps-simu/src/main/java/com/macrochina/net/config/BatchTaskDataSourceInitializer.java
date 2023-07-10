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
import java.util.HashMap;
import java.util.Map;

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

    @Value("${Privatekey}")
    private static String privateKeyPath;


    //存放着密钥信息
    static public Map<String, String> privateKeyMap = new HashMap<>();

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
            if (resource.exists() && resource.isReadable())
                populator.addScripts(resource);
            else if (autoInsert.exists()) {
                populator.addScripts(autoInsert);
            }
        }
        populator.addScripts(businessScript);
        return populator;
    }

//    /**
//     * 获取指令文件下的私钥
//     *
//     * @return
//     * @throws IOException
//     */
//    @Bean
//    public static String getPrivateKey() throws IOException {
//        String privateKey = "";
//        FileSystemResource resource = new FileSystemResource(privateKeyPath);
//        if (resource.exists() && resource.isReadable()) {
//            InputStream stream = resource.getInputStream();
//            privateKey = printContent(stream);
//            privateKeyMap.put("privateKey", privateKey);
//        }
//        return privateKey;
//    }
//
//    /**
//     * 输出输入流的内容
//     *
//     * @param is
//     * @return
//     * @throws IOException
//     */
//    private static String printContent(InputStream is) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        StringBuffer buffer = new StringBuffer();
//        String line;
//        while ((line = br.  readLine()) != null) {
//            buffer.append(line);
//        }
//        System.out.println(buffer);
//        if (is != null) {
//            is.close();
//        }
//        if (br != null) {
//            br.close();
//        }
//        return String.valueOf(buffer);
//    }
}
