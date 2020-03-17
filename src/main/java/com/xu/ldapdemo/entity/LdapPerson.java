package com.xu.ldapdemo.entity;

import lombok.Data;

@Data
public class LdapPerson {
    private String givenName;
    private String sn;
    private String displayName;
    private String uid;
    private String cn;
}
