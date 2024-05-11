package AmazeingGui.GuiControlPanel;

import AmazeingGui.Coords;
import AmazeingGui.MazeData;

import java.awt.*;
import java.awt.image.BufferedImage;

class MazeToImageConverter {
    static BufferedImage convertMazeToImage(MazeData mazeData)
    {
        BufferedImage image = new BufferedImage(mazeData.width()*5, mazeData.height()*5, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2D = image.createGraphics();

        for (int i = 0; i < mazeData.height(); i++) {
            for (int j = 0; j < mazeData.width(); j++)
            {
                Coords temp = new Coords(j, i);

                if(temp.equals(mazeData.entry()))
                    g2D.setPaint(Color.GREEN);
                else if (temp.equals(mazeData.exit()))
                    g2D.setPaint(Color.RED);
                else if (mazeData.maze()[i][j] == MazeData.Wall)
                    g2D.setPaint(Color.BLACK);
                else
                    g2D.setPaint(Color.WHITE);

                g2D.fillRect(j*5, i*5, 5, 5);
            }
        }

        g2D.dispose();

        return image;
    }
}
