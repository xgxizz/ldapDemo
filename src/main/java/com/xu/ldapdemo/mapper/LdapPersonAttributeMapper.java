package com.xu.ldapdemo.mapper;

import com.xu.ldapdemo.entity.LdapPerson;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

@Service
public class LdapPersonAttributeMapper implements AttributesMapper<LdapPerson>{
    @Override
    public LdapPerson mapFromAttributes(Attributes attributes) throws NamingException {
        LdapPerson person = new LdapPerson();
        person.setGivenName((String) attributes.get("givenName").get());
        person.setCn((String) attributes.get("cn").get());
        person.setDisplayName((String) attributes.get("displayName").get());
        person.setSn((String) attributes.get("sn").get());
        person.setUid((String) attributes.get("uid").get());
        return person;
    }
}
