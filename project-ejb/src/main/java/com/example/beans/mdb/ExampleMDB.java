package com.example.beans.mdb;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.example.repository.Person;
import com.example.repository.PersonRepository;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ExampleQueue") })
public class ExampleMDB implements MessageListener {

    @Inject
    private PersonRepository personRepository;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            Person person = getPerson(textMessage.getText());
            System.out.println("Received message: " + person.toString());

            personRepository.storePerson(person);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private Person getPerson(String serialized) {
        Map<String, String> data = new HashMap<>();
        String[] parts = serialized.split(";");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                data.put(key, value);
            }
        }

        Person person = new Person.Builder(data.get("id")).name(data.get("name")).email(data.get("email"))
                .age(Integer.parseInt(data.get("age"))).build();

        return person;
    }

}
