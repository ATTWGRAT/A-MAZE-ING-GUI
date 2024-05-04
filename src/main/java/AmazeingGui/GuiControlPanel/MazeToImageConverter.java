package AmazeingGui.GuiControlPanel;

import AmazeingGui.MazeData;

import java.awt.*;
import java.awt.image.BufferedImage;

class MazeToImageConverter {
    static BufferedImage convertMazeToImage(MazeData mazeData)
    {
        BufferedImage image = new BufferedImage(mazeData.width()*10, mazeData.height()*10, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2D = image.createGraphics();

        for (int i = 0; i < mazeData.height(); i++) {
            for (int j = 0; j < mazeData.width(); j++)
            {
                switch (mazeData.maze()[i][j])
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
}
