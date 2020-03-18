package com.xu.ldapdemo.person.repository;

import com.xu.ldapdemo.person.entity.Person;

public interface PersonRepo {
    void create(Person person);
    void update(Person person);
}
