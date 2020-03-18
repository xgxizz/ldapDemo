package com.xu.ldapdemo.repository.organ;

import com.xu.ldapdemo.organPerson.repository.LdapOrganRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapOrganRepositoryTest {

    @Autowired
    private LdapOrganRepository organRepository;
    @Test
    public void testGetAllorganizationalUnit(){
        System.out.println(organRepository.getOrganList());
        //结果：
        /*[
            LdapOrgan(organName=People, location=null, desc=null),
            LdapOrgan(organName=Group, location=null, desc=null),
            LdapOrgan(organName=organ0317, location=beijing, desc=null),
            LdapOrgan(organName=0317, location=beijing, desc=null)
         ]*/
    }

    @Test
    public void testFindOrgan(){
        System.out.println(organRepository.findOrgan("ou=0317,ou=organ0317"));
        //结果：LdapOrgan(organName=0317, location=beijing, desc=null)
    }
}
