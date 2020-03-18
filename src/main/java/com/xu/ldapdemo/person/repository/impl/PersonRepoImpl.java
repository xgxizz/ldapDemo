package com.xu.ldapdemo.person.repository.impl;

import com.xu.ldapdemo.person.entity.Person;
import com.xu.ldapdemo.person.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;

@Service
public class PersonRepoImpl implements PersonRepo {

    @Autowired
    private LdapTemplate ldapTemplate;

    public static final String BASE_DN = "cn=person0318";
    @Override
    public void create(Person p) {
        Name dn = buildDn(p);
        DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues("objectclass", new String[] {"top", "person"});//此处传递多个值，注意这个用法，比较重要

        mapToContext(p, context);
        ldapTemplate.bind(context);
    }

    @Override
    public void update(Person p) {
        Name dn = buildDn(p);
        DirContextOperations context = ldapTemplate.lookupContext(dn);
        mapToContext(p, context);
        ldapTemplate.modifyAttributes(context);
    }

    protected void mapToContext(Person p, DirContextOperations context){
        context.setAttributeValue("cn", p.getFullName());
        context.setAttributeValue("sn", p.getLastName());
        context.setAttributeValue("telephoneNumber",p.getTelephoneNumber());
        context.setAttributeValue("description", p.getDescription());
    }


    /**
     * 注意，创建DN成功的前提是目录中存在下面方法中的<p>c<p/> <p>ou<p/> <p>cn<p/>这三个目录项，
     * 该方法可以扩展其他字段，但前提是ldap中存在对应的目录结构。
     * @param p
     * @return
     */
    protected Name buildDn(Person p) {
        return LdapNameBuilder.newInstance(BASE_DN)
                .add("c", p.getCountry())
                .add("o", p.getCompany())
                .add("cn", p.getFullName())
                .build();
    }
}
