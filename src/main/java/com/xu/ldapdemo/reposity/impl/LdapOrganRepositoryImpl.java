package com.xu.ldapdemo.reposity.impl;

import com.xu.ldapdemo.entity.LdapOrgan;
import com.xu.ldapdemo.mapper.LdapOrganAttributeMapper;
import com.xu.ldapdemo.reposity.LdapOrganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class LdapOrganRepositoryImpl implements LdapOrganRepository {
    @Autowired
    private LdapTemplate ldapTemplate;
    @Override
    public List<LdapOrgan> getOrganList() {
        return ldapTemplate.search(query().where("objectclass").is("organizationalUnit"), new LdapOrganAttributeMapper());
    }

    @Override
    public LdapOrgan findOrgan(String dn) {
        return ldapTemplate.lookup(dn, new LdapOrganAttributeMapper());
    }
}
