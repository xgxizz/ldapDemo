package com.xu.ldapdemo.reposity;

import com.xu.ldapdemo.entity.LdapPerson;

import java.util.List;

public interface LdapPersonRepository {

    List<LdapPerson> getPersonNamesByLastName(String lastName);
    void create(LdapPerson person);
    void delete(LdapPerson person);
}
