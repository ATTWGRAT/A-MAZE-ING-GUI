package AmazeingGui.Listeners;

import AmazeingGui.Coords;
import AmazeingGui.CustomEvent.EventFactory;
import AmazeingGui.CustomEvent.EventType;
import AmazeingGui.CustomEventManager;
import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;
import AmazeingGui.MazeData;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MazeMouseActionListener extends MouseInputAdapter {
    private final ControlPanelComposite controlPanelComposite;

    public MazeMouseActionListener(ControlPanelComposite controlPanelComposite) {
        this.controlPanelComposite = controlPanelComposite;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Coords newCoords = new Coords(e.getX() / 8, e.getY()/8);

        MazeData data = controlPanelComposite.getMazeData();

        if(data.width() <= e.getX()/8
                || data.height() <= e.getY()/8)
            return;

        if(controlPanelComposite.isChoosingEntry())
        {
            if(controlPanelComposite.getMazeData().getExit() != null)
                controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);

            controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, true);

            controlPanelComposite.changeChoosingEntry();

            Coords oldCoords = data.getEntry();

            data.setEntry(newCoords);

            CustomEventManager.getInstance().callEvent(
                    EventType.coordsChangeEvent,
                    EventFactory.createCoordsChangeEvent(oldCoords, newCoords, false)
            );

        }
        else if (controlPanelComposite.isChoosingExit())
        {
            if(controlPanelComposite.getMazeData().getEntry() != null)
                controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);

            controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, true);

            controlPanelComposite.changeChoosingExit();

            Coords oldCoords = data.getExit();

            data.setExit(newCoords);

            CustomEventManager.getInstance().callEvent(
                    EventType.coordsChangeEvent,
                    EventFactory.createCoordsChangeEvent(oldCoords, newCoords, true)
            );

        }

    }
}
