package test2;

import com.macrochina.net.FpsApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author xhd
 * @date 2023/6/26
 */

@SpringBootTest(classes = com.macrochina.net.FpsApplication.class )
public class demo {

    @Resource
    StringEncryptor encryptor;

    @Test
    public void encrypt() {
//        String url = encryptor.encrypt("url: jdbc:mysql://192.168.0.127:3306/sj?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai\n");
        String username = encryptor.encrypt("root");
        String pwd = encryptor.encrypt("password");
//        System.out.println("url = " + url);
        System.out.println("username = " + username);
        System.out.println("pwd = " + pwd);
    }
}