package com.xu.ldapdemo.repository.person;

import com.xu.ldapdemo.entity.LdapPerson;
import com.xu.ldapdemo.reposity.LdapPersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapPersonRepositoryTest {

    @Autowired
    private LdapPersonRepository repository;
    @Test
    public void testGetPersonByLastName(){
        System.out.println(repository.getPersonNamesByLastName("againSecondName"));
    }

    @Test
    public void testCreate(){
        LdapPerson person = new LdapPerson();
        person.setGivenName("xgx_givenName2");
        person.setCn("xgx_cn2");
        person.setDisplayName("xgx_displayName2");
        person.setSn("xgx_sn2");
        person.setUid("xgx_uid2");
        repository.create(person);
    }

    @Test
    public void testDelete(){
        LdapPerson person = repository.getPersonNamesByLastName("xu").get(0);
        repository.delete(person);
    }
}
