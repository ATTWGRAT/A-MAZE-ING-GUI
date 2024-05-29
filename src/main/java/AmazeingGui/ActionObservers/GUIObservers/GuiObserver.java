package AmazeingGui.ActionObservers.GUIObservers;

import AmazeingGui.ActionObservers.ActionObserver;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;

abstract class GuiObserver implements ActionObserver {
    final ControlPanelComposite controlPanelComposite;
    public GuiObserver(ControlPanelComposite controlPanelComposite)
    {
        this.controlPanelComposite = controlPanelComposite;
    }
}
