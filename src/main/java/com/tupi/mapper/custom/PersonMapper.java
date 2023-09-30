package com.tupi.mapper.custom;

import com.tupi.data.vo.v2.PersonVO2;
import com.tupi.models.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    public PersonVO2 convertEntityToVO (Person person) {
        PersonVO2 vo = new PersonVO2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        vo.setBirthDay(new Date());
        return vo;
    }

    public Person convertVOToEntity (PersonVO2 personVO) {
        Person entity = new Person();
        entity.setId(personVO.getId());
        entity.setAddress(personVO.getAddress());
        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setGender(personVO.getGender());
        return entity;
    }

}
