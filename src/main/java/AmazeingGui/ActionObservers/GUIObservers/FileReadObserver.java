package AmazeingGui.ActionObservers.GUIObservers;

import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;
import AmazeingGui.MazeDataSingleton;

import javax.swing.*;

public class FileReadObserver extends GuiObserver {

    public FileReadObserver(ControlPanelComposite controlPanelComposite) {
        super(controlPanelComposite);
    }

    @Override
    public void call() {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(this::call);
            return;
        }
        MazeDataSingleton data = MazeDataSingleton.getInstance();

        controlPanelComposite.getFilenameLabel().setText("Źródło: " + data.getSource());

        controlPanelComposite.changeMazeImage();

        controlPanelComposite.setStatusLabel("<html>Odczytano labirynt z: " + data.getSource() + "<br/>" +
                "<table><tr><td>Szerokość: " + data.width() + "</td><td> Wejście: " + data.getEntry() +
                "</td></tr><tr><td>Wysokość: " + data.height() + "</td><td> Wyjście: " + data.getExit() +
                "</td></tr></table>", false);

        controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
        controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);
        controlPanelComposite.setButtonState(ButtonEnum.writeFileButton, true);

        controlPanelComposite.setButtonState(ButtonEnum.solveButton, data.getExit() != null && data.getEntry() != null);
    }
}
