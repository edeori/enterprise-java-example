package com.example.pojo;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.repository.Person;
import com.example.repository.PersonRepository;

@Named
@ApplicationScoped
public class PersonService {

    @Inject
    private PersonRepository personRepository;

    public List<Person> getAllPeople() {
        return personRepository.findAllPerson();
    }

}
