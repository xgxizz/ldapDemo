package com.xu.ldapdemo.organPerson.mapper.contextMapper;

import com.xu.ldapdemo.organPerson.entity.LdapOrganPerson;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;

import javax.naming.NamingException;

public class LdapPersonContextMapper implements ContextMapper<LdapOrganPerson> {
    @Override
    public LdapOrganPerson mapFromContext(Object o) throws NamingException {
        DirContextAdapter context = (DirContextAdapter) o;
        LdapOrganPerson person = new LdapOrganPerson();
        person.setUid(context.getStringAttribute("uid"));
        person.setCn(context.getStringAttribute("cn"));
        person.setSn(context.getStringAttribute("sn"));
        person.setDisplayName(context.getStringAttribute("displayName"));
        person.setGivenName(context.getStringAttribute("givenName"));
        person.setDescription(context.getStringAttribute("description"));
        return person;
    }
}
