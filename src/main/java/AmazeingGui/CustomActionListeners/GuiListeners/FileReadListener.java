package AmazeingGui.CustomActionListeners.GuiListeners;

import AmazeingGui.ApplicationGUI;
import AmazeingGui.CustomEvent.CustomEvent;
import AmazeingGui.CustomEvent.MazeFileReadEvent;
import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;
import AmazeingGui.MazeData;

import javax.swing.*;

public class FileReadListener extends GuiObserver {

    public FileReadListener(ApplicationGUI gui)
    {
        super(gui);
    }

    @Override
    public void call(CustomEvent event) {
        if(!SwingUtilities.isEventDispatchThread())
            SwingUtilities.invokeLater(() -> call(event));
        else
        {
            if (!(event instanceof MazeFileReadEvent mazeEvent))
                return;

            MazeData newData = mazeEvent.getNewMaze();
            String source = mazeEvent.getSource();
            ControlPanelComposite controlPanelComposite = this.gui.getControlPanel();

            controlPanelComposite.getFilenameLabel().setText("Źródło: " + source);

            controlPanelComposite.changeMazeData(mazeEvent.getNewMaze());

            controlPanelComposite.setStatusLabel("<html>Odczytano labirynt z: " + source + "<br/>" +
                    "<table><tr><td>Szerokość: " + newData.width() + "</td><td> Wejście: " + newData.getEntry() +
                    "</td></tr><tr><td>Wysokość: " + newData.height() + "</td><td> Wyjście: " + newData.getExit() +
                    "</td></tr></table>", false);

            controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.writeFileButton, true);

            if (newData.getExit() != null && newData.getEntry() != null)
                controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);
        }
    }
}
