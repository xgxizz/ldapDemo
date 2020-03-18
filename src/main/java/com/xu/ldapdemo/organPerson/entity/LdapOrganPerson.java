package com.xu.ldapdemo.organPerson.entity;

import lombok.Data;

@Data
public class LdapOrganPerson {
    private String givenName;
    private String sn;
    private String displayName;
    private String uid;
    private String cn;
    private String description;
}
