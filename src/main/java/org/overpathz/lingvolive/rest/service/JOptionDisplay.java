package org.overpathz.lingvolive.rest.service;

import javax.swing.*;

public class JOptionDisplay implements IDisplay {
    @Override
    public void display() {
        JOptionPane.showMessageDialog(null, Buffer.getInstance().getValue());
    }
}
