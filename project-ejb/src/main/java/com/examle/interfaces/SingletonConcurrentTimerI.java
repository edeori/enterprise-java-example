package com.examle.interfaces;

import javax.ejb.Local;

@Local
public interface SingletonConcurrentTimerI {

    int getCounter();

    void incrementCounter();

    void resetCounter();

}
