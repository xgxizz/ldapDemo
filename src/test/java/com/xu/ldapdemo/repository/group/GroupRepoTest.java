package com.xu.ldapdemo.repository.group;

import com.xu.ldapdemo.group.entity.Group;
import com.xu.ldapdemo.group.repository.GroupRepo;
import com.xu.ldapdemo.person.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepoTest {
    @Autowired
    private GroupRepo groupRepo;

    @Test
    public void testCreateGroup(){
        Group group = new Group();
        group.setName("xgx0319group_unique");
        Set memberSet = new HashSet();
        memberSet.add("cn=xuguoxing2,c=CN,cn=person0318,dc=zhidaoauto,dc=com");
        group.setMembers(memberSet);
        groupRepo.createGroup(group);
    }
    @Test
    public void testAddMemberToGroup(){
        com.xu.ldapdemo.odm.entity.Person person = new com.xu.ldapdemo.odm.entity.Person();
        person.setFullName("xgx_zhyu");
        //person.setCountry("CN");
        person.setCompany("zhyu");
        groupRepo.addMemberToGroup("xgx0319group_unique",person);
    }

    @Test
    public void testRemoveMemberToGroup(){
        com.xu.ldapdemo.odm.entity.Person person = new com.xu.ldapdemo.odm.entity.Person();
        person.setFullName("xgx_zhyu");
        //person.setCountry("CN");
        person.setCompany("zhyu");
        groupRepo.removeMemberFromGroup("xgx0319group_unique",person);
    }
}
