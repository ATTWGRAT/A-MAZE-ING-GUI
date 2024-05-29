package AmazeingGui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MazeToImageConverter {
    public static BufferedImage getImageFromMaze()
    {
        MazeDataSingleton data = MazeDataSingleton.getInstance();

        BufferedImage image = new BufferedImage(data.width()*8, data.height()*8, BufferedImage.TYPE_BYTE_INDEXED);

        Graphics2D g2D = image.createGraphics();

        for (int i = 0; i < data.height(); i++) {
            for (int j = 0; j < data.width(); j++)
            {
                Coords temp = new Coords(j, i);

                if(temp.equals(data.getEntry()))
                    g2D.setPaint(Color.GREEN);
                else if (temp.equals(data.getExit()))
                    g2D.setPaint(Color.RED);
                else if (data.getMaze()[i][j] == MazeDataSingleton.Wall)
                    g2D.setPaint(Color.BLACK);
                else
                    g2D.setPaint(Color.WHITE);

                g2D.fillRect(j*8, i*8, 8, 8);
            }
        }

        g2D.dispose();

        return image;
    }

}
