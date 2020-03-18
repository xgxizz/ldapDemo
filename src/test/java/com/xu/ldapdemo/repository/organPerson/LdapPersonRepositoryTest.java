package com.xu.ldapdemo.repository.organPerson;

import com.xu.ldapdemo.organPerson.entity.LdapOrganPerson;
import com.xu.ldapdemo.organPerson.repository.LdapPersonRepository;
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
        LdapOrganPerson person = new LdapOrganPerson();
        person.setGivenName("xgx_givenName3");
        person.setCn("xgx_cn3");
        person.setDisplayName("xgx_displayName3");
        person.setSn("xgx_sn3");
        person.setUid("xgx_uid3");
        person.setDescription("xgx_3");
        repository.create(person);
    }

    @Test
    public void testDelete(){
        LdapOrganPerson person = repository.getPersonNamesByLastName("xu").get(0);
        repository.delete(person);
    }

    /**
     * 注意：更新时候可以只填写部分属性值，但是uid不能为空
     */
    @Test
    public void testUpdateUseRebind(){
        LdapOrganPerson person = new LdapOrganPerson();
        person.setGivenName("xgx_givenName2");
        person.setCn("xgx_cn2");
        person.setDisplayName("xgx_displayName2222");
        person.setSn("xgx_sn2");
        person.setUid("xgx_uid2");
        repository.updateUseRebind(person);
    }

    @Test
    public void testUpdateUseModifyAttr(){
        LdapOrganPerson person = new LdapOrganPerson();
//        person.setGivenName("xgx_givenName2");
//        person.setCn("xgx_cn2");
//        person.setDisplayName("xgx_displayName2222");
//        person.setSn("xgx_sn2");
        person.setUid("xgx_uid2");
        person.setDescription("update by modifyAttr 0318");
        repository.updateUseModifyAttributes(person);
    }

    @Test
    public void testFindByPrimaryKey_UseContextMapper(){
        LdapOrganPerson person = new LdapOrganPerson();
        person.setGivenName("xgx_givenName3");
        person.setCn("xgx_cn3");
        person.setDisplayName("xgx_displayName3");
        person.setSn("xgx_sn3");
        person.setUid("xgx_uid3");
        person.setDescription("xgx_3");
        repository.findByPrimaryKey(person);
        System.out.println(person);
    }
}
