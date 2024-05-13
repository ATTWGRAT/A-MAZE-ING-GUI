package AmazeingGui.Listeners;

import AmazeingGui.Coords;
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
            controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, true);

            controlPanelComposite.setNewEntry(newCoords);

            controlPanelComposite.changeChoosingEntry();

            data.setEntry(newCoords);
        }
        else if (controlPanelComposite.isChoosingExit())
        {
            controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, true);

            controlPanelComposite.setNewExit(newCoords);

            controlPanelComposite.changeChoosingExit();

            data.setExit(newCoords);
        } else {
            return;
        }

        controlPanelComposite.setStatusLabel("<html>" + "<table><tr><td>Szerokość: " + data.width() + "</td><td> Wejście: " + data.entry() +
                "</td></tr><tr><td>Wysokość: " + data.height() + "</td><td> Wyjście: " + data.exit() +
                "</td></tr></table>", false);
    }
}
