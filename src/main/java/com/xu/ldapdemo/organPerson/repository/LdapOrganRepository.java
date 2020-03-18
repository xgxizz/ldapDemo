package com.xu.ldapdemo.organPerson.repository;

import com.xu.ldapdemo.organPerson.entity.LdapOrgan;

import java.util.List;

public interface LdapOrganRepository {

    List<LdapOrgan> getOrganList();
    LdapOrgan findOrgan(String dn);
}
