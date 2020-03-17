package com.xu.ldapdemo.mapper;

import com.xu.ldapdemo.entity.LdapOrgan;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

public class LdapOrganAttributeMapper implements AttributesMapper<LdapOrgan> {


    @Override
    public LdapOrgan mapFromAttributes(Attributes attributes) throws NamingException {
        LdapOrgan organ = new LdapOrgan();
        organ.setOrganName((String) attributes.get("ou").get());
        if (attributes.get("l") != null)
            organ.setLocation((String) attributes.get("l").get());
        if (attributes.get("description") != null)
        organ.setDesc((String) attributes.get("description").get());
        return organ;
    }
}
