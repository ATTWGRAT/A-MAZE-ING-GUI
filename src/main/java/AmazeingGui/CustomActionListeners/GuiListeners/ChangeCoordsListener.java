package AmazeingGui.CustomActionListeners.GuiListeners;

import AmazeingGui.Coords;
import AmazeingGui.CustomActionListeners.CustomActionListener;
import AmazeingGui.CustomEvent.ChangeCoordsEvent;
import AmazeingGui.CustomEvent.CustomEvent;
import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;
import AmazeingGui.MazeData;

public class ChangeCoordsListener implements CustomActionListener {
    private final ControlPanelComposite controlPanelComposite;

    public ChangeCoordsListener(ControlPanelComposite controlPanelComposite)
    {
        this.controlPanelComposite = controlPanelComposite;
    }

    @Override
    public void call(CustomEvent event) {
        if(!(event instanceof ChangeCoordsEvent changeCoordsEvent))
            return;

        Coords oldCoords = changeCoordsEvent.getOldCoords();
        Coords newCoords = changeCoordsEvent.getNewCoords();
        MazeData data = controlPanelComposite.getMazeData();

        if(!controlPanelComposite.isChoosingExit() && !controlPanelComposite.isChoosingEntry())
        {
            controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);

            if(data.getExit() != null && data.getEntry() != null)
                controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);

        }
        if(changeCoordsEvent.isExit())
            controlPanelComposite.setNewExit(oldCoords, newCoords);
        else
            controlPanelComposite.setNewEntry(oldCoords, newCoords);

        controlPanelComposite.setStatusLabel("<html>" + "<table><tr><td>Szerokość: " + data.width() + "</td><td> Wejście: " + data.getEntry() +
                "</td></tr><tr><td>Wysokość: " + data.height() + "</td><td> Wyjście: " + data.getExit() +
                "</td></tr></table>", false);
    }
}
