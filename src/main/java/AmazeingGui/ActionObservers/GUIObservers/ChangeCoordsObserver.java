package AmazeingGui.ActionObservers.GUIObservers;

import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;
import AmazeingGui.MazeDataSingleton;

import javax.swing.*;

public class ChangeCoordsObserver extends GuiObserver {
    public ChangeCoordsObserver(ControlPanelComposite controlPanelComposite)
    {
        super(controlPanelComposite);
    }

    @Override
    public void call() {
        if(!SwingUtilities.isEventDispatchThread())
            SwingUtilities.invokeLater(this::call);
        else {
            MazeDataSingleton data = MazeDataSingleton.getInstance();

            if (!controlPanelComposite.isChoosingExit() && !controlPanelComposite.isChoosingEntry()) {
                controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
                controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);

                if (data.getExit() != null && data.getEntry() != null)
                    controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);

            }

            controlPanelComposite.changeMazeImage();

            controlPanelComposite.setStatusLabel("<html>" + "<table><tr><td>Szerokość: " + data.width() + "</td><td> Wejście: " + data.getEntry() +
                    "</td></tr><tr><td>Wysokość: " + data.height() + "</td><td> Wyjście: " + data.getExit() +
                    "</td></tr></table>", false);
        }
    }
}
