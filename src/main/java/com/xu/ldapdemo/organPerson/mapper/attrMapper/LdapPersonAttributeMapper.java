package com.xu.ldapdemo.organPerson.mapper.attrMapper;

import com.xu.ldapdemo.organPerson.entity.LdapOrganPerson;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

@Service
public class LdapPersonAttributeMapper implements AttributesMapper<LdapOrganPerson>{
    @Override
    public LdapOrganPerson mapFromAttributes(Attributes attributes) throws NamingException {
        LdapOrganPerson person = new LdapOrganPerson();
        person.setGivenName((String) attributes.get("givenName").get());
        person.setCn((String) attributes.get("cn").get());
        person.setDisplayName((String) attributes.get("displayName").get());
        person.setSn((String) attributes.get("sn").get());
        person.setUid((String) attributes.get("uid").get());
        return person;
    }
}
