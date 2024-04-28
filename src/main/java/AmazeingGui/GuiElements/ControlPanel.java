package AmazeingGui.GuiElements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ControlPanel {
    private final JPanel jPanel;
    private final ControlPanelButton solveButton;
    private final ControlPanelButton chooseEntranceButton;
    private final ControlPanelButton chooseExitButton;
    private final ControlPanelButton chooseFileButton;
    private final JRadioButton binToggleButton;
    private final JLabel fileNameLabel;

    public ControlPanel()
    {
        Image solveImage;
        Image exitImage;
        Image entryImage;
        Image folderImage;

        //Loading icons from resources
        try
        {
            solveImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("accept.png")));
            exitImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("exit.png")));
            entryImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("log-in.png")));
            folderImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("folder.png")));

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        this.jPanel = new JPanel();

        // Creating buttons
        this.chooseFileButton = new ControlPanelButton(" Wybierz plik", new ImageIcon(folderImage));
        this.chooseExitButton = new ControlPanelButton(" Wybierz wyjście", new ImageIcon(exitImage));
        this.chooseEntranceButton = new ControlPanelButton(" Wybierz wejście", new ImageIcon(entryImage));
        this.solveButton = new ControlPanelButton(" Rozwiąż", new ImageIcon(solveImage));


        //Creating label for filename
        this.fileNameLabel = new JLabel("Brak Wybranego Pliku");
        this.fileNameLabel.setOpaque(true);
        this.fileNameLabel.setBackground(new Color(78, 80, 82));
        this.fileNameLabel.setHorizontalAlignment(0); // 0 = centered
        this.fileNameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        //Creating binary file toggle button
        this.binToggleButton = new JRadioButton(" Plik binarny");
        this.binToggleButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.binToggleButton.setHorizontalAlignment(0);
        this.binToggleButton.setBackground(new Color(78, 80, 82));
        this.binToggleButton.setOpaque(true);

        setupGrid();

    }

    private void setupGrid()
    {
        this.jPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1;
        gbc.weighty = 0.2;
        gbc.gridy = 0; //Row 1
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        this.jPanel.add(this.fileNameLabel, gbc);

        gbc.gridy++; //Row 2

        gbc.gridwidth = 1;
        gbc.weightx = 0.5;

        this.jPanel.add(this.chooseFileButton, gbc);

        gbc.gridx = 1; //Column 2

        this.jPanel.add(this.binToggleButton, gbc);

        gbc.gridy++; //Row 3 Column 1
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.gridx = 0;

        this.jPanel.add(this.chooseEntranceButton, gbc);

        gbc.gridy++; //Row 4

        this.jPanel.add(this.chooseExitButton, gbc);

        gbc.gridy++; //Row 5

        this.jPanel.add(this.solveButton, gbc);
    }

    public JPanel getjPanel() {
        return jPanel;
    }
}
