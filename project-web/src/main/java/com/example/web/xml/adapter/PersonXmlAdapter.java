package com.example.web.xml.adapter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.example.repository.Person;
import com.example.web.xml.PersonXmlRepresentation;

@Named
@ApplicationScoped
public class PersonXmlAdapter {

    public static PersonXmlRepresentation marshal(Person person) {
        PersonXmlRepresentation xmlRepresentation = new PersonXmlRepresentation();
        xmlRepresentation.setId(person.getId());
        xmlRepresentation.setName(person.getName());
        xmlRepresentation.setAge(person.getAge());
        xmlRepresentation.setEmail(person.getEmail());
        return xmlRepresentation;
    }

    public static Person unmarshal(PersonXmlRepresentation xmlRepresentation) {
        Person.Builder builder = new Person.Builder(xmlRepresentation.getId());
        builder.name(xmlRepresentation.getName());
        builder.age(xmlRepresentation.getAge());
        builder.email(xmlRepresentation.getEmail());
        return builder.build();
    }
}
