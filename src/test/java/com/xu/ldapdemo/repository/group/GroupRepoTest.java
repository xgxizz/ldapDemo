package com.xu.ldapdemo.repository.group;

import com.xu.ldapdemo.group.repository.GroupRepo;
import com.xu.ldapdemo.person.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepoTest {
    @Autowired
    private GroupRepo groupRepo;
    @Test
    public void testAddMemberToGroup(){
        Person person = new Person();
        person.setFullName("xgx_zhyu");
        person.setCountry("CN");
        person.setCompany("zhyu");
        groupRepo.addMemberToGroup("testGroup",person);
    }
}
