package AmazeingGui.GuiControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class InternalMazeView {
    private final JPanel mazeViewPanel;
    private final BufferedImage mazeImage;

    InternalMazeView(BufferedImage mazeImage)
    {
        this.mazeImage = mazeImage;

        mazeViewPanel = new JPanel();

        JLabel imageLabel = new JLabel(new ImageIcon(mazeImage));

        mazeViewPanel.setPreferredSize(new Dimension(mazeImage.getWidth(), mazeImage.getHeight()));
        mazeViewPanel.setLayout(new BorderLayout());
        mazeViewPanel.add(imageLabel, BorderLayout.CENTER);

    }

    BufferedImage getMazeImage() {
        return mazeImage;
    }

    JPanel getMazeViewPanel() {
        return mazeViewPanel;
    }
}
