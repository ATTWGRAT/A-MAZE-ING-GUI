package AmazeingGui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MazeToImageConverter {
    public static BufferedImage convertMazeToImage(MazeData mazeData)
    {
        BufferedImage image = new BufferedImage(mazeData.width()*8, mazeData.height()*8, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2D = image.createGraphics();

        for (int i = 0; i < mazeData.height(); i++) {
            for (int j = 0; j < mazeData.width(); j++)
            {
                Coords temp = new Coords(j, i);

                if(temp.equals(mazeData.getEntry()))
                    g2D.setPaint(Color.GREEN);
                else if (temp.equals(mazeData.getExit()))
                    g2D.setPaint(Color.RED);
                else if (mazeData.getMaze()[i][j] == MazeData.Wall)
                    g2D.setPaint(Color.BLACK);
                else
                    g2D.setPaint(Color.WHITE);

                g2D.fillRect(j*8, i*8, 8, 8);
            }
        }

        g2D.dispose();

        return image;
    }

    public static void setExitToImage(BufferedImage image, Coords oldCoords, Coords newCoords, MazeData mazeData)
    {
        changeOldCoordsColor(image, oldCoords, mazeData);
        Graphics2D g2D = (Graphics2D) image.getGraphics();

        g2D.setPaint(Color.RED);
        g2D.fillRect(newCoords.x*8, newCoords.y*8, 8, 8);

        g2D.dispose();
    }

    public static void setEntryToImage(BufferedImage image, Coords oldCoords, Coords newCoords, MazeData mazeData)
    {
        changeOldCoordsColor(image, oldCoords, mazeData);
        Graphics2D g2D = (Graphics2D) image.getGraphics();

        g2D.setPaint(Color.GREEN);
        g2D.fillRect(newCoords.x*8, newCoords.y*8, 8, 8);

        g2D.dispose();
    }

    private static void changeOldCoordsColor(BufferedImage image, Coords oldCoords, MazeData mazeData) {
        Graphics2D g2D = (Graphics2D) image.getGraphics();

        if(oldCoords != null)
        {
            if(mazeData.getMaze()[oldCoords.y][oldCoords.x] == MazeData.Wall)
                g2D.setPaint(Color.BLACK);
            else
                g2D.setPaint(Color.WHITE);

            g2D.fillRect(oldCoords.x*8, oldCoords.y*8, 8, 8);
        }
    }

}
