package AmazeingGui.CustomActionListeners.GuiListeners;

import AmazeingGui.CustomActionListeners.CustomActionListener;
import AmazeingGui.CustomEvent.CustomEvent;
import AmazeingGui.CustomEvent.MazeFileReadEvent;
import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;
import AmazeingGui.MazeData;

public class FileReadListener implements CustomActionListener {
    private final ControlPanelComposite controlPanelComposite;

    public FileReadListener(ControlPanelComposite controlPanelComposite)
    {
        this.controlPanelComposite = controlPanelComposite;
    }

    @Override
    public void call(CustomEvent event) {
        if(!(event instanceof MazeFileReadEvent mazeEvent))
            return;

        MazeData newData = mazeEvent.getNewMaze();
        String source = mazeEvent.getSource();

        if(newData == null){
            controlPanelComposite.setStatusLabel("Podano pusty plik!", true);
            return;
        }

        controlPanelComposite.getFilenameLabel().setText("Źródło: " + source);

        controlPanelComposite.changeMazeData(mazeEvent.getNewMaze());

        controlPanelComposite.setStatusLabel("<html>Odczytano labirynt z: " + source + "<br/>" +
                "<table><tr><td>Szerokość: " + newData.width() + "</td><td> Wejście: " + newData.getEntry() +
                "</td></tr><tr><td>Wysokość: " + newData.height() + "</td><td> Wyjście: " + newData.getExit() +
                "</td></tr></table>", false);

        controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
        controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);
        controlPanelComposite.setButtonState(ButtonEnum.writeFileButton, true);

        if(newData.getExit() != null && newData.getEntry() != null)
            controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);
    }
}
