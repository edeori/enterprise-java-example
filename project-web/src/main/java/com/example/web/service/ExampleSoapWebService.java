package com.example.web.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class ExampleSoapWebService {

    @WebMethod
    public String sayHello(@WebParam(name = "name") String name) {
        return "Hello, " + name + "!";
    }
}
