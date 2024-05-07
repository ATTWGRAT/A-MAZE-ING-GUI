package AmazeingGui.GuiControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class MazeViewPanel extends JLabel {
    private final BufferedImage mazeImage;

    MazeViewPanel(BufferedImage mazeImage)
    {
        super(new ImageIcon(mazeImage));
        this.mazeImage = mazeImage;
        setPreferredSize(new Dimension(mazeImage.getWidth(), mazeImage.getHeight()));

    }

    BufferedImage getMazeImage() {
        return mazeImage;
    }



}
