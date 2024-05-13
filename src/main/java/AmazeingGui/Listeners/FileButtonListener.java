package AmazeingGui.Listeners;

import AmazeingGui.Exceptions.MazeException;
import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.MazeData;
import AmazeingGui.MazeFileReader;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class FileButtonListener implements ActionListener {
    private final ControlPanelComposite controlPanelComposite;

    FileButtonListener(ControlPanelComposite controlPanelComposite) {
        this.controlPanelComposite = controlPanelComposite;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(new File("."));

        int returnVal = fileChooser.showOpenDialog(controlPanelComposite.getJScrollPane());

        File currentFile;

        if(returnVal == JFileChooser.APPROVE_OPTION)
            currentFile = fileChooser.getSelectedFile();
        else if(returnVal == JFileChooser.ERROR_OPTION) {
            controlPanelComposite.setStatusLabel("Błąd przy otwieraniu pliku!", true);
            return;
        }
        else {
            return;
        }

        try {
            if(MazeFileReader.isFileBinary(currentFile)) {
                throw new UnsupportedOperationException("Pliki binarne nie zaimplementowane!");
            }
            else {
                MazeData newData = MazeFileReader.readTxtToMazeData(currentFile);

                if(newData == null){
                    controlPanelComposite.setStatusLabel("Podano pusty plik!", true);
                    return;
                }

                controlPanelComposite.changeMazeData(newData);

                controlPanelComposite.getFilenameLabel().setText("Plik: " + currentFile.getName());

                controlPanelComposite.setStatusLabel("<html>Otwarto plik: " + currentFile.getName() + "<br/>" +
                        "<table><tr><td>Szerokość: " + newData.width() + "</td><td> Wejście: " + newData.entry() +
                        "</td></tr><tr><td>Wysokość: " + newData.height() + "</td><td> Wyjście: " + newData.exit() +
                        "</td></tr></table>", false);

                controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
                controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);

                if(!(newData.exit().equals(MazeData.Nowhere) || newData.entry().equals(MazeData.Nowhere)))
                    controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);
            }

        } catch (IOException ex) {
            controlPanelComposite.setStatusLabel("Błąd IO podczas czytania z pliku: " + ex.getClass().getName(), true);
        } catch (MazeException ex) {
            controlPanelComposite.setStatusLabel("Błąd podczas wczytywania labiryntu: " + ex.getMessage(), true);
        }

    }
}
