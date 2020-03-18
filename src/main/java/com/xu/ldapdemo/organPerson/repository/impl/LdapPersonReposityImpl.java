package com.xu.ldapdemo.organPerson.repository.impl;

import com.xu.ldapdemo.organPerson.entity.LdapOrganPerson;
import com.xu.ldapdemo.organPerson.mapper.attrMapper.LdapPersonAttributeMapper;
import com.xu.ldapdemo.organPerson.mapper.contextMapper.LdapPersonContextMapper;
import com.xu.ldapdemo.organPerson.repository.LdapPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.naming.directory.*;
import java.util.List;
import java.util.Vector;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class LdapPersonReposityImpl implements LdapPersonRepository {

    @Autowired
    private LdapTemplate ldapTemplate;
    @Override
    public List<LdapOrganPerson> getPersonNamesByLastName(String lastName) {
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
    public void create(LdapOrganPerson person) {
        Name dn = buildDn(person);
        //DirContext dirContext = DirContextAdapter.
        ldapTemplate.bind(dn, null, buildAttributes(person));
    }

    @Override
    public void delete(LdapOrganPerson person) {
        Name dn = buildDn(person);
        ldapTemplate.unbind(dn);
    }

    @Override
    public void updateUseRebind(LdapOrganPerson person) {
        Name dn = buildDn(person);
        ldapTemplate.rebind(dn, null, buildAttributes(person));
    }

    @Override
    public void updateUseModifyAttributes(LdapOrganPerson person) {
        Name dn = buildDn(person);
        Attribute attr = new BasicAttribute("description", person.getDescription());
        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
        ldapTemplate.modifyAttributes(dn, new ModificationItem[] {item});
    }

    @Override
    public LdapOrganPerson findByPrimaryKey(LdapOrganPerson person) {
        Name dn = buildDn(person);
        return ldapTemplate.lookup(dn, new LdapPersonContextMapper());
    }

    private Attributes buildAttributes(LdapOrganPerson person) {
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocattr = new BasicAttribute("objectclass");
        ocattr.add("posixAccount");
        ocattr.add("top");
        ocattr.add("inetOrgPerson");
        attrs.put(ocattr);
        attrs.put("cn", person.getCn());
        attrs.put("sn", person.getSn());
        attrs.put("displayName", person.getDisplayName());
        attrs.put("givenName", person.getGivenName());
        attrs.put("description", person.getDescription());
        attrs.put("uid", "123");
        attrs.put("gidNumber","88");
        attrs.put("uidNumber","23424");
        attrs.put("homeDirectory", "ou=people");
        return attrs;
    }
    protected Name buildDn(LdapOrganPerson p) {
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
