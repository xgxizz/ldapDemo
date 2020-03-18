package com.xu.ldapdemo.odm.entity;

import lombok.Data;
import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"person", "top"}, base = "cn=person0318,o=zhyu")
public class Person {

    @Id
    private Name dn;

    @Attribute(name="cn")
    //@DnAttribute(value="cn", index=2)
    private String fullName;

    @Attribute(name = "sn")
    //@DnAttribute(value = "sn", index = 2)
    private String lastName;

    // No @Attribute annotation means this will be bound to the LDAP attribute
    // with the same value
    private String description;

//    @Attribute(name = "c")
//    @DnAttribute(value = "c", index=1)
//    private String country;

    @Attribute(name = "o")
    //@DnAttribute(value="o", index=1)
    @Transient
    private String company;

    @Transient
    private String someUnmappedField;
    // ...more attributes below

    /**
     * index说明
     * 完整的dn:cn=jsdj,o=zhyu,cn=person0318,dc=zhidaoauto,dc=com
     * 树状结构：
     * dc=zhidaoauto,dc=com
     *      cn=person0318
     *          o=zhyu              ..................................................index=1
     *              cn=jsdj         ..................................................index=0(从最底层开始向上，从0递增，到base结束)
     */

}
