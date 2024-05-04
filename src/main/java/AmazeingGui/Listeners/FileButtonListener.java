package AmazeingGui.Listeners;

import AmazeingGui.GuiControlPanel.ControlPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileButtonListener implements ActionListener {
    private final ControlPanel controlPanelComposite;
    private File currentFile;

    public FileButtonListener(ControlPanel controlPanelComposite) {
        this.controlPanelComposite = controlPanelComposite;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();

        int returnVal = fileChooser.showOpenDialog(controlPanelComposite.getJScrollPane());

        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            currentFile = fileChooser.getSelectedFile();
            controlPanelComposite.getFilenameLabel().setText("Plik: " + currentFile.getName());
            controlPanelComposite.setStatusLabel("Otwarto plik: " + currentFile.getName(), false);
        }
        else if(returnVal == JFileChooser.ERROR_OPTION)
        {
            controlPanelComposite.setStatusLabel("Błąd przy otwieraniu pliku!", true);
        }


        //Add reading file to mazeView
    }
}
