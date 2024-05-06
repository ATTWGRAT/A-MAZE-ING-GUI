package AmazeingGui.GuiControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class MazeViewPanel extends JPanel {
    private final JLabel imageLabel;
    private final BufferedImage mazeImage;

    MazeViewPanel(BufferedImage mazeImage)
    {
        super();
        this.mazeImage = mazeImage;
        imageLabel = new JLabel(new ImageIcon(mazeImage));

        setPreferredSize(new Dimension(mazeImage.getWidth(), mazeImage.getHeight()));
        setLayout(new BorderLayout());
        add(imageLabel, BorderLayout.CENTER);

    }

    BufferedImage getMazeImage() {
        return mazeImage;
    }



}
