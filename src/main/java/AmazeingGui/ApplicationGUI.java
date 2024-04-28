package AmazeingGui;

import AmazeingGui.GuiElements.ControlPanel;
import AmazeingGui.GuiElements.MazePanel;
import AmazeingGui.GuiElements.StatusLabelPanel;

import javax.swing.*;
import java.awt.*;

public class ApplicationGUI {
    private final JFrame mainGUIFrame;
    private final ControlPanel controlPanel;
    private final StatusLabelPanel statusLabelPanel;
    private final MazePanel mazePanel;

    public ApplicationGUI()
    {
        this.mainGUIFrame = new JFrame();
        this.mainGUIFrame.setTitle("A-MAZE-ING");
        this.mainGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainGUIFrame.setSize(1200, 700);
        this.mainGUIFrame.setResizable(false);
        this.mainGUIFrame.setLayout(new GridBagLayout());
        this.mainGUIFrame.setLocationRelativeTo(null);

        this.controlPanel = new ControlPanel();
        this.statusLabelPanel = new StatusLabelPanel();
        this.mazePanel = new MazePanel();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 0.8;
        gbc.weighty = 0.75;
        this.mainGUIFrame.add(this.mazePanel.getScrollPane(), gbc);

        gbc.weighty = 0.25;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        this.mainGUIFrame.add(this.statusLabelPanel, gbc);

        gbc.weightx = 0.2;
        gbc.weighty = 1;
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        this.mainGUIFrame.add(this.controlPanel.getjPanel(), gbc);

    }

    public void start()
    {
        this.mainGUIFrame.setVisible(true);
    }
}
