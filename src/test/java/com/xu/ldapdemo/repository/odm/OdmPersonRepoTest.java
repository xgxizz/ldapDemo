package com.xu.ldapdemo.repository.odm;

import com.xu.ldapdemo.odm.entity.Person;
import com.xu.ldapdemo.odm.repostory.OdmPersonRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OdmPersonRepoTest {

    @Autowired
    private OdmPersonRepo personRepo;
    @Test
    public void testCreate(){
        Person person = new Person();
        person.setFullName("jsdj");
        person.setLastName("jsdj");
        person.setCompany("zhyu");
        person.setDescription("Beautiful mountains and rivers");
        person.setSomeUnmappedField("null");
        personRepo.create(person);
    }

    @Test
    public void testUpdate(){
        Person person = new Person();
        person.setFullName("jsdj");
        person.setLastName("jsdj");
        person.setCompany("zhyu");
        person.setDescription("Beautiful mountains and rivers 江山多娇");
        person.setSomeUnmappedField("null");
        personRepo.update(person);
    }

    @Test
    public void testDelete()  {
        Person person = new Person();
        person.setFullName("jsdj_deleting");
        person.setLastName("jsdj");
        person.setCompany("zhyu");
        person.setDescription("Beautiful mountains and rivers 江山多娇");
        person.setSomeUnmappedField("null");
        personRepo.create(person);
        System.out.println("马上删除，此句话打断点。保证先创建");
        personRepo.delete(person);
    }

    @Test
    public void testFindAll(){
        List<Person> all = personRepo.findAll();
        System.out.println(all);

    }

    @Test
    public void testFindByLastName(){
        List<Person> jsdj = personRepo.findByLastName("jsdj");
        System.out.println(jsdj);

        //结果：
        //[Person(dn=cn=jsdj,o=zhyu,cn=person0318, fullName=zhyu, lastName=jsdj, description=Beautiful mountains and rivers 江山多娇, company=person0318, someUnmappedField=null)]
    }
}
