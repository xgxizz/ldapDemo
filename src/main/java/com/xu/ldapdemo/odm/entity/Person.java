package com.xu.ldapdemo.odm.entity;

import lombok.Data;
import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"person", "top"}, base = "cn=person0318")
public class Person {

    @Id
    private Name dn;

    @Attribute(name="cn")
    @DnAttribute(value="cn", index=2)
    private String fullName;

    @Attribute(name = "sn")
    private String lastName;

    // No @Attribute annotation means this will be bound to the LDAP attribute
    // with the same value
    private String description;

    @Attribute(name = "o")
    @DnAttribute(value="o", index=1)
    //@DnAttribute 表示dn属性到对象类字段的映射。因为dn中没有company这个字段，只含有“o”这个字段，我们需要将“o”对应的值映射给“company”，
    // index表示在dn中的位置,说明见代码最后
    @Transient
    private String company;

    @Transient  //@Transient注释被用来指示字段应该由对象目录映射被忽略并且不被映射到底层LDAP属性
    private String someUnmappedField;
    // ...more attributes below
    /**
     * index说明
     * 完整的dn:cn=jsdj,o=zhyu,cn=person0318,dc=zhidaoauto,dc=com
     * 树状结构：
     * dc=zhidaoauto,dc=com
     *      cn=person0318           ..................................................index=0
     *          o=zhyu              ..................................................index=1
     *              cn=jsdj         ..................................................index=2
     */

}
