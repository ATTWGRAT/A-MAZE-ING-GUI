package AmazeingGui.GuiElements;

import javax.swing.*;
import java.awt.*;

public class ControlPanel {
    private final JPanel jPanel;
    private final JButton solveButton;
    private final JButton chooseEntranceButton;
    private final JButton chooseExitButton;
    private final JButton chooseFileButton;
    private final JToggleButton binToggleButton;
    private final JLabel fileNameLabel;

    public ControlPanel()
    {
        this.jPanel = new JPanel();
        this.binToggleButton = new JToggleButton();
        this.chooseFileButton = new JButton();
        this.chooseExitButton = new JButton();
        this.chooseEntranceButton = new JButton();
        this.solveButton = new JButton();
        this.fileNameLabel = new JLabel("Brak Wybranego Pliku");
        this.jPanel.setBackground(this.chooseFileButton.getBackground());

        this.jPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1;
        gbc.weighty = 0.2;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        this.fileNameLabel.setOpaque(true);
        this.fileNameLabel.setBackground(this.chooseFileButton.getBackground());
        this.jPanel.add(this.fileNameLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;

        this.jPanel.add(this.chooseFileButton, gbc);

        gbc.gridx = 1;

        this.jPanel.add(this.binToggleButton, gbc);

        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;

        this.jPanel.add(this.chooseEntranceButton, gbc);

        gbc.gridy++;

        this.jPanel.add(this.chooseExitButton, gbc);

        gbc.gridy++;

        this.jPanel.add(this.solveButton, gbc);

    }

    public JPanel getjPanel() {
        return jPanel;
    }
}
