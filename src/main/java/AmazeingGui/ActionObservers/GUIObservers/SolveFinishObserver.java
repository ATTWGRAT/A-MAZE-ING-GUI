package AmazeingGui.ActionObservers.GUIObservers;

import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;
import AmazeingGui.MazeDataSingleton;

import javax.swing.*;

public class SolveFinishObserver extends GuiObserver{
    public SolveFinishObserver(ControlPanelComposite controlPanelComposite)
    {
        super(controlPanelComposite);
    }


    @Override
    public void call() {
        if(!SwingUtilities.isEventDispatchThread())
        {
            SwingUtilities.invokeLater(this::call);
            return;
        }
        controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);
        controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);
        controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
        controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, true);
        controlPanelComposite.setButtonState(ButtonEnum.writeFileButton, true);

        MazeDataSingleton data = MazeDataSingleton.getInstance();

        if(!data.isSolved())
            controlPanelComposite.setStatusLabel("Nie udało się znaleźć wyjścia z labiryntu! Spróbuj zmienić pozycję wejścia / wyjścia.", true);
        else {
            controlPanelComposite.repaintMazeImage();
            controlPanelComposite.setStatusLabel("<html>Znaleziono rozwiązanie labiryntu!" + "<table><tr><td>Szerokość: " + data.width() + "</td><td> Wejście: " + data.getEntry() +
                    "</td></tr><tr><td>Wysokość: " + data.height() + "</td><td> Wyjście: " + data.getExit() +
                    "</td></tr></table>", false);
        }
    }
}
