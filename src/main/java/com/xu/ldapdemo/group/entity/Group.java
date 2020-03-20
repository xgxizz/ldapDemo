package com.xu.ldapdemo.group.entity;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.Set;

@Data
@Entry(objectClasses = {"groupOfUniqueNames","top"}, base = "ou=Groups")
public class Group {
    @Id
    private Name dn;

    @Attribute(name="cn")
    @DnAttribute(value = "cn", index = 0)
    private String name;

    @Attribute(name="uniqueMember")
    private Set<Name> members;

}