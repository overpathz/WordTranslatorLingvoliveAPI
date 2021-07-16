package org.overpathz.lingvolive.rest.service;

import javax.swing.*;
import java.awt.*;

public class SwingUIReader implements IReader {
    @Override
    public String getWord() {
        return JOptionPane.showInputDialog("Введите слово для перевода: ");
    }
}
