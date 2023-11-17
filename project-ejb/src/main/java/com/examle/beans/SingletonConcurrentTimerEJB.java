package com.examle.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;

import com.examle.interfaces.SingletonConcurrentTimerI;

@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class SingletonConcurrentTimerEJB implements SingletonConcurrentTimerI {

    private int counter = 0;

    @PostConstruct
    public void initialize() {
        System.out.println("Singleton bean initialized.");
    }

    @Lock(LockType.READ)
    @Override
    public int getCounter() {
        return counter;
    }

    @Lock(LockType.WRITE)
    @Override
    public void incrementCounter() {
        counter++;
    }

    @Lock(LockType.WRITE)
    @Override
    public void resetCounter() {
        counter = 0;
    }

    @Timeout
    public void timeoutHandler(Timer timer) {
        System.out.println("Timeout occurred. Current counter value: " + counter);
    }

    @Schedule(second = "*/10", minute = "*", hour = "*")
    public void scheduledTask() {
        System.out.println("Scheduled task executed. Current counter value: " + counter);
    }

    @PreDestroy
    public void destroy() {
        // Add any cleanup or resource release code here
        System.out.println("Singleton bean destroyed.");
    }

}
