package AmazeingGui;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            ApplicationGUI mainGui = new ApplicationGUI();
            mainGui.start();

        });
    }
}