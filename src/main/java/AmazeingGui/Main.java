package AmazeingGui;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarculaLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            ApplicationGUI mainGui = new ApplicationGUI();
            mainGui.start();

        });
    }
}