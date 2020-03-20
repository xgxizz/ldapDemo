package com.xu.ldapdemo.group.repository.impl;

import com.xu.ldapdemo.group.entity.Group;
import com.xu.ldapdemo.group.repository.GroupRepo;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.naming.Name;
import javax.naming.ldap.LdapName;
@Service
public class GroupRepoImpl implements GroupRepo, BaseLdapNameAware {

    @Resource
    private LdapTemplate ldapTemplate;
    //@Autowired
    private LdapName baseLdapPath = LdapUtils.newLdapName("ou=Groups");

    @Override
    public Group createGroup(Group group) {
        ldapTemplate.create(group);
        return group;
    }

    @Override
    public void addMemberToGroup(String groupName, com.xu.ldapdemo.odm.entity.Person person) {
        Name groupDn = buildGroupDn(groupName);
        Name userDn = buildPersonDn(
                person.getFullName(),
                person.getCompany());
                //person.getCountry()););

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        //ctx.setAttributeValue("cn", groupName);
        ctx.addAttributeValue("uniqueMember", userDn);
        ldapTemplate.modifyAttributes(ctx);
    }

    @Override
    public void removeMemberFromGroup(String groupName, com.xu.ldapdemo.odm.entity.Person person) {
        Name groupDn = buildGroupDn(groupName);
        Name userDn = buildPersonDn(
                person.getFullName(),
                person.getCompany());
                //person.getCountry());

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.removeAttributeValue("member", userDn);

        ldapTemplate.modifyAttributes(ctx);
    }
    private Name buildGroupDn(String groupName) {
        return LdapNameBuilder.newInstance("ou=Groups")
                .add("cn", groupName).build();
    }

    private Name buildPersonDn(String fullname, String company) {
        return LdapNameBuilder.newInstance(baseLdapPath)
                //.add("c", country)
                .add("ou", company)
                .add("cn", fullname)
                .build();
    }

    @Override
    public void setBaseLdapPath(LdapName ldapName) {
        this.setBaseLdapPath(baseLdapPath);
    }

}
