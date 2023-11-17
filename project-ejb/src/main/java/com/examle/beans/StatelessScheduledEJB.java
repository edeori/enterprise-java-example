package com.examle.beans;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.examle.interfaces.SingletonConcurrentTimerI;
import com.examle.interfaces.StatelessScheduledI;
import com.example.configuration.Configuration;

@Stateless(name = "StatelessScheduledEJB")
public class StatelessScheduledEJB implements StatelessScheduledI {

    @Inject
    SingletonConcurrentTimerI SingletonConcurrentTimerEJB;

    @Override
    @Schedule(second = "*/10", minute = "*", hour = "*")
    public void consume() {
        System.out
                .println("StatelessScheduledEJB invoked to display count: " + SingletonConcurrentTimerEJB.getCounter());
        System.out.println("********************************");
        System.out.println(Configuration.getProperties().getProperty("value"));
        System.out.println("********************************");
    }

}
