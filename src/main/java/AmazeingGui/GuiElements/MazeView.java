package AmazeingGui.GuiElements;

import AmazeingGui.MazeData;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MazeView {
    private final MazeData mazeData;
    private final JPanel mazeViewPanel;
    private final BufferedImage mazeImage;

    MazeView(MazeData mazeData)
    {
        mazeViewPanel = new JPanel();
        this.mazeData = mazeData;
        mazeImage = generateMazeImage(mazeData);

        JLabel imageLabel = new JLabel(new ImageIcon(mazeImage));

        mazeViewPanel.setPreferredSize(new Dimension(10*mazeData.getWidth(), 10*mazeData.getHeight()));
        mazeViewPanel.setLayout(new BorderLayout());
        mazeViewPanel.add(imageLabel, BorderLayout.CENTER);

    }

    private BufferedImage generateMazeImage(MazeData mazeData)
    {
        BufferedImage image = new BufferedImage(mazeData.getWidth()*10, mazeData.getHeight()*10, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2D = image.createGraphics();

        for (int i = 0; i < mazeData.getHeight(); i++) {
            for (int j = 0; j < mazeData.getWidth(); j++)
            {
                switch (mazeData.getMaze()[i][j])
                {
                    case MazeData.Wall:
                        g2D.setPaint(Color.BLACK);
                        break;
                    case MazeData.Entry:
                        g2D.setPaint(Color.GREEN);
                        break;
                    case MazeData.Exit:
                        g2D.setPaint(Color.RED);
                        break;
                    default:
                        g2D.setPaint(Color.WHITE);
                        break;
                }

                g2D.fillRect(j*10, i*10, 10, 10);
            }
        }

        g2D.dispose();

        return image;
    }

    MazeData getMazeData() {
        return mazeData;
    }

    BufferedImage getMazeImage() {
        return mazeImage;
    }

    JPanel getMazeViewPanel() {
        return mazeViewPanel;
    }
}
