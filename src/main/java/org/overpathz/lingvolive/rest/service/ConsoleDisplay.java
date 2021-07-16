package org.overpathz.lingvolive.rest.service;

public class ConsoleDisplay implements IDisplay {
    @Override
    public void display() {
        System.out.println(Buffer.getInstance().getValue());
    }
}
