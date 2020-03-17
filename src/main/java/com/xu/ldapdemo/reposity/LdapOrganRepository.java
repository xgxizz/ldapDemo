package com.xu.ldapdemo.reposity;

import com.xu.ldapdemo.entity.LdapOrgan;

import java.util.List;

public interface LdapOrganRepository {

    List<LdapOrgan> getOrganList();
    LdapOrgan findOrgan(String dn);
}
