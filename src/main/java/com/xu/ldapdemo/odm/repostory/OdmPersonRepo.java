package com.xu.ldapdemo.odm.repostory;

import com.xu.ldapdemo.odm.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;
@Service
public class OdmPersonRepo {
   @Autowired
   private LdapTemplate ldapTemplate;

   public Person create(Person person) {
      ldapTemplate.create(person);
      return person;
   }

   public Person findByUid(String uid) {
      return ldapTemplate.findOne(query().where("uid").is(uid), Person.class);
   }

   public void update(Person person) {
      ldapTemplate.update(person);
   }

   public void delete(Person person) {
      ldapTemplate.delete(person);
   }

   public List<Person> findAll() {
      return ldapTemplate.findAll(Person.class);
   }

   public List<Person> findByLastName(String lastName) {
      return ldapTemplate.find(query().where("sn").is(lastName), Person.class);
   }
}