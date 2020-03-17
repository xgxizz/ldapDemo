package com.xu.ldapdemo.compare;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringLDAPMethod {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Test
    public void getAll(){
        List<String> searchResult = ldapTemplate.search(
                query().where("objectclass").is("organizationalUnit"),
                new AttributesMapper<String>() {
                    public String mapFromAttributes(Attributes attrs)
                            throws NamingException {
                        return attrs.get("ou").get().toString();
                    }
                });
        System.out.println(searchResult);
    }
}
