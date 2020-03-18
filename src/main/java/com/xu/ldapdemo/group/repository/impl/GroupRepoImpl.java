package com.xu.ldapdemo.group.repository.impl;

import com.xu.ldapdemo.group.repository.GroupRepo;
import com.xu.ldapdemo.person.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.naming.Name;
import javax.naming.ldap.LdapName;
@Service
public class GroupRepoImpl implements GroupRepo, BaseLdapNameAware {

    @Resource
    private LdapTemplate ldapTemplate;
    //@Autowired
    //private LdapName baseLdapPath;

    @Override
    public void addMemberToGroup(String groupName, Person person) {
        Name groupDn = buildGroupDn(groupName);
        Name userDn = buildPersonDn(
                person.getFullName(),
                person.getCompany(),
                person.getCountry());

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.addAttributeValue("member", userDn);

        ldapTemplate.update(ctx);
    }

    @Override
    public void removeMemberFromGroup(String groupName, Person person) {
        Name groupDn = buildGroupDn(groupName);
        Name userDn = buildPersonDn(
                person.getFullName(),
                person.getCompany(),
                person.getCountry());

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.removeAttributeValue("member", userDn);

        ldapTemplate.update(ctx);
    }
    private Name buildGroupDn(String groupName) {
        return LdapNameBuilder.newInstance("ou=Group")
                .add("cn", groupName).build();
    }

    private Name buildPersonDn(String fullname, String company, String country) {
        return LdapNameBuilder.newInstance("cn=person0318")
                .add("c", country)
                .add("ou", company)
                .add("cn", fullname)
                .build();
    }

    @Override
    public void setBaseLdapPath(LdapName ldapName) {

    }

//    @Override
//    public void setBaseLdapPath(LdapName ldapName) {
//        this.setBaseLdapPath(baseLdapPath);
//    }
}
