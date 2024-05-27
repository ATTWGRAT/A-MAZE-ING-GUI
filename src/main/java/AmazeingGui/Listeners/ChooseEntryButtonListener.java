package AmazeingGui.Listeners;

import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;
import AmazeingGui.MazeData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseEntryButtonListener implements ActionListener {
    private final ControlPanelComposite controlPanelComposite;

    public ChooseEntryButtonListener(ControlPanelComposite controlPanelComposite)
    {
        this.controlPanelComposite = controlPanelComposite;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean val = controlPanelComposite.isChoosingEntry();

        MazeData data = controlPanelComposite.getMazeData();

        if(data.getExit() != null
                && data.getEntry() != null)
            controlPanelComposite.setButtonState(ButtonEnum.solveButton, val);

        controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, val);
        controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, val);

        controlPanelComposite.changeChoosingEntry();
    }
}
