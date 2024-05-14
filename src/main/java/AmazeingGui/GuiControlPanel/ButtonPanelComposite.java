package AmazeingGui.GuiControlPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

class ButtonPanelComposite {
    private final JPanel controlJPanel;
    private final ControlPanelButton solveButton;
    private final ControlPanelButton chooseEntranceButton;
    private final ControlPanelButton chooseExitButton;
    private final ControlPanelButton chooseFileButton;
    private final ControlPanelButton writeFileButton;
    private final JLabel fileNameLabel;

    ButtonPanelComposite()
    {
        Image solveImage;
        Image exitImage;
        Image entryImage;
        Image folderImage;
        Image writingImage;

        //Loading icons from resources
        try
        {
            solveImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("accept.png")));
            exitImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("exit.png")));
            entryImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("log-in.png")));
            folderImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("folder.png")));
            writingImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("writing.png")));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        this.controlJPanel = new JPanel();

        // Creating buttons
        this.chooseFileButton = new ControlPanelButton(" Wybierz plik", new ImageIcon(folderImage));
        this.chooseExitButton = new ControlPanelButton(" Wybierz wyjście", new ImageIcon(exitImage));
        this.chooseEntranceButton = new ControlPanelButton(" Wybierz wejście", new ImageIcon(entryImage));
        this.solveButton = new ControlPanelButton(" Rozwiąż", new ImageIcon(solveImage));
        this.writeFileButton = new ControlPanelButton(" Wypisz labirynt", new ImageIcon(writingImage));

        //Creating label for filename
        this.fileNameLabel = new JLabel("Brak Wybranego Pliku");
        this.fileNameLabel.setOpaque(true);
        this.fileNameLabel.setBackground(new Color(78, 80, 82));
        this.fileNameLabel.setHorizontalAlignment(0); // 0 = centered
        this.fileNameLabel.setFont(new Font("Arial", Font.BOLD, 18));


        setupGrid();
    }

    private void setupGrid()
    {
        this.controlJPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1;
        gbc.weighty = 0.2;
        gbc.gridwidth = 2;
        gbc.gridy = 0; //Row 1
        gbc.gridx = 0;

        this.controlJPanel.add(this.fileNameLabel, gbc);

        gbc.gridy++; //Row 2
        gbc.weightx = 0.5;
        gbc.gridwidth = 1;

        this.controlJPanel.add(this.chooseFileButton, gbc);

        gbc.gridx = 1;

        this.controlJPanel.add(this.writeFileButton, gbc);

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.gridy++; //Row 3

        this.controlJPanel.add(this.chooseEntranceButton, gbc);

        gbc.gridy++; //Row 4

        this.controlJPanel.add(this.chooseExitButton, gbc);

        gbc.gridy++; //Row 5

        this.controlJPanel.add(this.solveButton, gbc);
    }

    JPanel getControlJPanel() {
        return controlJPanel;
    }

    void setButtonState(ButtonEnum button, boolean state)
    {
        switch(button)
        {
            case solveButton -> this.solveButton.setEnabled(state);
            case chooseEntranceButton -> this.chooseEntranceButton.setEnabled(state);
            case chooseExitButton -> this.chooseExitButton.setEnabled(state);
            case chooseFileButton -> this.chooseFileButton.setEnabled(state);
            case writeFileButton -> this.writeFileButton.setEnabled(state);
        }
    }

    void setButtonListener(ButtonEnum button, ActionListener listener)
    {
        switch(button)
        {
            case solveButton -> this.solveButton.addActionListener(listener);
            case chooseEntranceButton -> this.chooseEntranceButton.addActionListener(listener);
            case chooseExitButton -> this.chooseExitButton.addActionListener(listener);
            case chooseFileButton -> this.chooseFileButton.addActionListener(listener);
            case writeFileButton -> this.writeFileButton.addActionListener(listener);
        }
    }

    JLabel getFilenameLabel() {
        return fileNameLabel;
    }
}
