package AmazeingGui.Listeners;

import AmazeingGui.CustomEventManager;
import AmazeingGui.EventType;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;
import AmazeingGui.MazeDataSingleton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolveButtonListener implements ActionListener {
    private final ControlPanelComposite controlPanelComposite;

    public SolveButtonListener(ControlPanelComposite controlPanelComposite)
    {
        this.controlPanelComposite = controlPanelComposite;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MazeDataSingleton.getInstance().solve();
        CustomEventManager.getInstance().callEvent(EventType.solveBeginEvent);
    }
}
