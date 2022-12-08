package com.macrochina.net.ldap;

import com.macrochina.net.config.LdapLdapConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.ConditionCriteria;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@Service
public class LdapServices
{

    @Autowired
    LdapLdapConfiguration config;
    public LDAPUser findOne(String x509SubjectName) throws FileNotFoundException {
        Map<String,Object> map = config.getParamMap();
        // String param =(String) map.get("param");
        // String value =(String) map.get("value");
        List<LDAPUser> lUser = config.getLdapTemplate(map).findAll(LDAPUser.class);
        // Object obj =  config.getLdapTemplate(map).findOne(LdapQueryBuilder.query().where(param).is(value), LDAPUser.class);
        // Object obj2 =  config.getLdapTemplate(map).findOne(LdapQueryBuilder.query().where("cn").is("user1"), LDAPUser.class);
        String str[] = x509SubjectName.split(",");
        if(null!=lUser){
            for(LDAPUser u:lUser){
                if(u.getId()==null){
                    continue;
                }
                if(str[0].trim().equalsIgnoreCase(u.getId().toString())){
                    return u;
                }
            }
        }
        return null;
    }
}
