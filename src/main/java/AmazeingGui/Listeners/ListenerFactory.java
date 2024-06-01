package AmazeingGui.Listeners;

import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;

import java.awt.event.ActionListener;

public class ListenerFactory {
    public static ActionListener createActionListener(ButtonEnum listeners, ControlPanelComposite CPC)
    {
        ActionListener newListener = null;

        switch (listeners)
        {
            case chooseFileButton -> newListener = new FileButtonListener(CPC);
            case chooseExitButton -> newListener = new ChooseExitButtonListener(CPC);
            case chooseEntranceButton -> newListener = new ChooseEntryButtonListener(CPC);
            case solveButton -> newListener = new SolveButtonListener(CPC);
        }

        return newListener;
    }
}
