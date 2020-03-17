package com.xu.ldapdemo.reposity.impl;

import com.xu.ldapdemo.entity.LdapPerson;
import com.xu.ldapdemo.mapper.LdapPersonAttributeMapper;
import com.xu.ldapdemo.reposity.LdapPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import java.util.List;
import java.util.Vector;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class LdapPersonReposityImpl implements LdapPersonRepository {

    @Autowired
    private LdapTemplate ldapTemplate;
    @Override
    public List<LdapPerson> getPersonNamesByLastName(String lastName) {
        Vector<String> vector = new Vector<String>();
        vector.add("posixAccount");
        vector.add("top");
        vector.add("inetOrgPerson");
        LdapQuery query = query()
                .base("ou=People")
                //.attributes("cn","sn")//筛选两项的值
//                .where("sn").is(lastName);
                .where("objectclass")
                .like("inetOrgPerson")
                .and("sn").is(lastName);
        return ldapTemplate.search(query, new LdapPersonAttributeMapper());
    }

    @Override
    public void create(LdapPerson person) {
        Name dn = buildDn(person);
        ldapTemplate.bind(dn, null, buildAttributes(person));
    }

    @Override
    public void delete(LdapPerson person) {
        Name dn = buildDn(person);
        ldapTemplate.unbind(dn);
    }

    private Attributes buildAttributes(LdapPerson person) {
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocattr = new BasicAttribute("objectclass");
        ocattr.add("posixAccount");
        ocattr.add("top");
        ocattr.add("inetOrgPerson");
        attrs.put(ocattr);
        attrs.put("cn", person.getCn());
        attrs.put("sn", person.getSn());
        attrs.put("uid", "123");
        attrs.put("gidNumber","88");
        attrs.put("uidNumber","23424");
        attrs.put("homeDirectory", "ou=people");
        return attrs;
    }
    protected Name buildDn(LdapPerson p) {
        return LdapNameBuilder.newInstance("ou=People")
//                .add("cn", p.getCn())
//                .add("displayName", p.getDisplayName())
//                .add("givenName", p.getGivenName())
//                .add("sn", p.getSn())
                .add("uid", p.getUid())
                //.add("uidNumber","789")
                .build();
    }

}
