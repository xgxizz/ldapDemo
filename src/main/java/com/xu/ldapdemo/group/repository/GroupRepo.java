package com.xu.ldapdemo.group.repository;

import com.xu.ldapdemo.group.entity.Group;

public interface GroupRepo {

    Group createGroup(Group group);
    void addMemberToGroup(String groupName, com.xu.ldapdemo.odm.entity.Person person);
    void removeMemberFromGroup(String groupName, com.xu.ldapdemo.odm.entity.Person person);
}
