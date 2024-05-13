package AmazeingGui.Listeners;

import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;
import AmazeingGui.MazeData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseExitButtonListener implements ActionListener {
    private final ControlPanelComposite controlPanelComposite;

    public ChooseExitButtonListener(ControlPanelComposite controlPanelComposite)
    {
        this.controlPanelComposite = controlPanelComposite;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean val = controlPanelComposite.isChoosingExit();

        MazeData data = controlPanelComposite.getMazeData();

        if(!(data.exit().equals(MazeData.Nowhere)
                || data.entry().equals(MazeData.Nowhere)))
            controlPanelComposite.setButtonState(ButtonEnum.solveButton, val);

        controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, val);
        controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, val);

        controlPanelComposite.changeChoosingExit();
    }
}
