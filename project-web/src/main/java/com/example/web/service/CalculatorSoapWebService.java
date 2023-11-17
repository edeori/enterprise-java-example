package com.example.web.service;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.examle.beans.CalculatorEJB;

/*
 * http://localhost:8080/ExampleWAR/CalculatorSoapWebService?wsdl
 */
@WebService
public class CalculatorSoapWebService {
    
    @Inject
    private CalculatorEJB calculatorEJB;
    
    @WebMethod
    public int add(@WebParam(name = "a") int a, @WebParam(name = "b") int b) {
        return calculatorEJB.add(a, b);
    }
    
    @WebMethod
    public int subtract(@WebParam(name = "a") int a, @WebParam(name = "b") int b) {
        return calculatorEJB.subtract(a, b);
    }
    
    @WebMethod
    public int multiply(@WebParam(name = "a") int a, @WebParam(name = "b") int b) {
        return calculatorEJB.multiply(a, b);
    }

}
