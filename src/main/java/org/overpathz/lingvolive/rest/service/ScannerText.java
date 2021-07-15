package org.overpathz.lingvolive.rest.service;

import java.util.Scanner;

public class ScannerText implements GetTextInterface{

    private String value;

    public ScannerText(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String text() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите текс для перевода: ");
        String text = scanner.next();

        return text;
    }
}
