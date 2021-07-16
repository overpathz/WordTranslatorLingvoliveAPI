package org.overpathz.lingvolive.rest.service;

import java.util.Scanner;

public class ConsoleReader implements IReader {
    @Override
    public String getWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите слово: ");
        String text = scanner.next();
        return text;
    }
}
