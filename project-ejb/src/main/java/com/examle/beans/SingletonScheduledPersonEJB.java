package com.examle.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.example.repository.Person;
import com.example.repository.PersonRepository;

@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class SingletonScheduledPersonEJB {

    @Inject
    private PersonRepository personRepository;

    @PostConstruct
    public void initialize() {
        System.out.println("Singleton bean initialized.");
    }

    @Schedule(second = "*/10", minute = "*", hour = "*")
    public void scheduledTask() {
        List<Person> allPerson = personRepository.findAllPerson();
        System.out.println("Scheduled task executed. Number of people in database: " + allPerson.size());
    }

}
