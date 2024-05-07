package AmazeingGui;

import AmazeingGui.GuiControlPanel.ButtonEnum;
import AmazeingGui.GuiControlPanel.ControlPanelComposite;
import AmazeingGui.Listeners.ListenerEnum;
import AmazeingGui.Listeners.ListenerFactory;

import javax.swing.*;
import java.awt.*;

public final class ApplicationGUI {
    private final JFrame mainGUIFrame;
    private final ControlPanelComposite controlPanelComposite;

    public ApplicationGUI()
    {
        this.mainGUIFrame = new JFrame();
        this.mainGUIFrame.setTitle("A-MAZE-ING");
        this.mainGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainGUIFrame.setSize(1200, 700);
        this.mainGUIFrame.setResizable(false);
        this.mainGUIFrame.setLayout(new GridBagLayout());
        this.mainGUIFrame.setLocationRelativeTo(null);

        this.controlPanelComposite = new ControlPanelComposite();

        JScrollPane mazeScrollPane = controlPanelComposite.getJScrollPane();

        JPanel statusLabelPanel = controlPanelComposite.getStatusLabelJPanel();

        JPanel buttonPanel = controlPanelComposite.getButtonPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 0.8;
        gbc.weighty = 0.75;
        this.mainGUIFrame.add(mazeScrollPane, gbc);

        gbc.weighty = 0.25;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        this.mainGUIFrame.add(statusLabelPanel, gbc);

        gbc.weightx = 0.2;
        gbc.weighty = 1;
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        this.mainGUIFrame.add(buttonPanel, gbc);

        controlPanelComposite.setButtonActionListener(
                ButtonEnum.chooseFileButton,
                ListenerFactory.createActionListener(ListenerEnum.FileButton, controlPanelComposite)
        );

    }

    public void start()
    {
        this.mainGUIFrame.setVisible(true);
    }

    public JFrame getMainGUIFrame() {
        return mainGUIFrame;
    }

    public ControlPanelComposite getControlPanel() {
        return controlPanelComposite;
    }

}
