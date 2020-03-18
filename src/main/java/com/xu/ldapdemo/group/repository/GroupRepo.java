package com.xu.ldapdemo.group.repository;

import com.xu.ldapdemo.person.entity.Person;

public interface GroupRepo {

    void addMemberToGroup(String groupName, Person p);
    void removeMemberFromGroup(String groupName, Person p);
}
