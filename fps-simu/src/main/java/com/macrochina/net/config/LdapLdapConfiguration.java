package com.macrochina.net.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class LdapLdapConfiguration {
    private static Log logger = LogFactory.getLog(LdapLdapConfiguration.class);
    /*public static void main(String[] args) throws FileNotFoundException {
        File file = ResourceUtils.getFile("C:\\Users\\wangxin\\Desktop\\ldap_config.yml");
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        Yaml props = new Yaml();
        Object obj = props.loadAs(in,Map.class);
        Map<String,Object> param = (Map<String, Object>) obj;
        System.out.println(    param.get("username"));
    }*/

    public Map<String,Object> getParamMap() throws FileNotFoundException {
        File file = ResourceUtils.getFile("/data/ldap_config.yml");
       // File file = ResourceUtils.getFile("C:\\Users\\wangxin\\Desktop\\ldap_config.yml");
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        Yaml props = new Yaml();
        Object obj = props.loadAs(in,Map.class);
        Map<String,Object> param = (Map<String, Object>) obj;
        logger.info("paramMap is "+param.toString());
        return param;
    }



    public LdapContextSource contextSource(Map<String,Object> param) throws FileNotFoundException {
      //  Map<String,Object> param = getParamMap();
        LdapContextSource contextSource = new LdapContextSource();
        Map<String, Object> config = new HashMap();
        contextSource.setUrl(param.get("urls")==null?"":param.get("urls").toString());
        contextSource.setBase(param.get("base")==null?"":param.get("base").toString());
        contextSource.setUserDn(param.get("username")==null?"":param.get("username").toString());
        contextSource.setPassword(param.get("password")==null?"":param.get("password").toString());
        //  解决 乱码 的关键一句
        config.put("java.naming.ldap.attributes.binary", "objectGUID");
        contextSource.setPooled(true);
        contextSource.setBaseEnvironmentProperties(config);
        contextSource.afterPropertiesSet();
        return contextSource;
    }
    public LdapTemplate getLdapTemplate(Map<String,Object> param) {
        LdapTemplate ldapTemplate = null;
        try {
            ldapTemplate = new LdapTemplate(contextSource(param));
        }catch(Exception e){
            logger.error("getLdapTemplate is null");
        }
        return ldapTemplate;
    }
}
