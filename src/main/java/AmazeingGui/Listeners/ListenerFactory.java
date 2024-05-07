package AmazeingGui.Listeners;

import AmazeingGui.GuiControlPanel.ControlPanelComposite;

import java.awt.event.ActionListener;

public class ListenerFactory {
    public static ActionListener createActionListener(ListenerEnum listeners, ControlPanelComposite CPC)
    {
        ActionListener newListener = null;

        switch (listeners)
        {
            case FileButton -> newListener = new FileButtonListener(CPC);
        }

        return newListener;
    }
}
