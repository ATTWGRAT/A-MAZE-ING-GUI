package AmazeingGui.CustomActionListeners.GuiListeners;

import AmazeingGui.ApplicationGUI;
import AmazeingGui.Coords;
import AmazeingGui.CustomEvent.ChangeCoordsEvent;
import AmazeingGui.CustomEvent.CustomEvent;
import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;
import AmazeingGui.MazeData;

import javax.swing.*;

public class ChangeCoordsListener extends GuiObserver {
    public ChangeCoordsListener(ApplicationGUI gui)
    {
        super(gui);
    }

    @Override
    public void call(CustomEvent event) {
        if(!SwingUtilities.isEventDispatchThread())
            SwingUtilities.invokeLater(() -> call(event));
        else {
            if (!(event instanceof ChangeCoordsEvent changeCoordsEvent))
                return;

            ControlPanelComposite controlPanelComposite = this.gui.getControlPanel();

            Coords oldCoords = changeCoordsEvent.getOldCoords();
            Coords newCoords = changeCoordsEvent.getNewCoords();
            MazeData data = controlPanelComposite.getMazeData();

            if (!controlPanelComposite.isChoosingExit() && !controlPanelComposite.isChoosingEntry()) {
                controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
                controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);

                if (data.getExit() != null && data.getEntry() != null)
                    controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);

            }
            if (changeCoordsEvent.isExit())
                controlPanelComposite.setNewExit(oldCoords, newCoords);
            else
                controlPanelComposite.setNewEntry(oldCoords, newCoords);

            controlPanelComposite.setStatusLabel("<html>" + "<table><tr><td>Szerokość: " + data.width() + "</td><td> Wejście: " + data.getEntry() +
                    "</td></tr><tr><td>Wysokość: " + data.height() + "</td><td> Wyjście: " + data.getExit() +
                    "</td></tr></table>", false);
        }
    }
}
