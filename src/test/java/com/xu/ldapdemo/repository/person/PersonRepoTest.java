package com.xu.ldapdemo.repository.person;

import com.xu.ldapdemo.person.entity.Person;
import com.xu.ldapdemo.person.repository.PersonRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepoTest {

    @Autowired
    private PersonRepo personRepo;
    @Test
    public void testCreate(){
        Person person = new Person();
        person.setFullName("xu_zhyu");
        person.setLastName("xu_zy");
        person.setCountry("CN");
        person.setCompany("zhyu");
        person.setTelephoneNumber("13303243567");
        person.setDescription("test DirContextAdepter2");
        personRepo.create(person);
    }
    @Test
    public void testUpdate(){
        Person person = new Person();
        person.setFullName("xgx_zhyu");
        person.setLastName("xgx_zy_UPDATE2222");
        person.setCountry("CN");
        person.setCompany("zhyu");
        person.setTelephoneNumber("13303233934");
        person.setDescription("test UPDATE ");
        personRepo.update(person);
    }
}
