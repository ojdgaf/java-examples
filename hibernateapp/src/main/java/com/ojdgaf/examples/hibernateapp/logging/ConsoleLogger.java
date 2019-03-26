package com.ojdgaf.examples.hibernateapp.logging;

public class ConsoleLogger implements Logger {
    @Override
    public void log(Object o) {
        System.out.println(o);
    }
}
