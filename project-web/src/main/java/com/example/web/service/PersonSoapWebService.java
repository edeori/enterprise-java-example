package com.example.web.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.example.pojo.PersonService;
import com.example.web.xml.PersonXmlRepresentation;
import com.example.web.xml.adapter.PersonXmlAdapter;

/*
 * http://localhost:8080/ExampleWAR/PersonSoapWebService?wsdl
 */
@WebService
public class PersonSoapWebService {

    @Inject
    private PersonService personService;

    @WebMethod
    public List<PersonXmlRepresentation> getPeople() {
        return personService.getAllPeople().stream().map(PersonXmlAdapter::marshal).collect(Collectors.toList());
    }

}
