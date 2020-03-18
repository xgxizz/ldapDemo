package com.xu.ldapdemo.organPerson.repository;

import com.xu.ldapdemo.organPerson.entity.LdapOrganPerson;

import java.util.List;

public interface LdapPersonRepository {

    List<LdapOrganPerson> getPersonNamesByLastName(String lastName);
    void create(LdapOrganPerson person);
    void delete(LdapOrganPerson person);
    void updateUseRebind(LdapOrganPerson person);
    void updateUseModifyAttributes(LdapOrganPerson person);
    LdapOrganPerson findByPrimaryKey(LdapOrganPerson person);
}
