package com.xu.ldapdemo.person.entity;

import lombok.Data;

@Data
public class Person {

    private String fullName;//cn
    private String lastName;//sn
    private String description;
    private String telephoneNumber;
    private String country;//c
    private String company;//ou


}
