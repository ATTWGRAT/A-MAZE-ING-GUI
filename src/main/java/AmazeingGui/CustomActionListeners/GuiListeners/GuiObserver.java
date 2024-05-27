package AmazeingGui.CustomActionListeners.GuiListeners;

import AmazeingGui.ApplicationGUI;
import AmazeingGui.CustomActionListeners.CustomActionListener;

public abstract class GuiObserver implements CustomActionListener {
    final ApplicationGUI gui;
    public GuiObserver(ApplicationGUI gui)
    {
        this.gui = gui;
    }
}
