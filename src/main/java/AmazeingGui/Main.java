package AmazeingGui;

import AmazeingGui.MazeData.MazeDataSingleton;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

class Main {
    public static void main(String[] args) {
        CustomEventManager.initialize();
        MazeDataSingleton.initialize();

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            ApplicationGUI mainGui = new ApplicationGUI();

            if(args.length > 0)
                (new Thread(() -> new CLI(args[0]))).start();
            else
                (new Thread(CLI::new)).start();

            mainGui.start();

        });
    }
}