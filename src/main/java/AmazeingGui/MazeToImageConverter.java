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

                if(temp.equals(mazeData.entry()))
                    g2D.setPaint(Color.GREEN);
                else if (temp.equals(mazeData.exit()))
                    g2D.setPaint(Color.RED);
                else if (mazeData.maze()[i][j] == MazeData.Wall)
                    g2D.setPaint(Color.BLACK);
                else
                    g2D.setPaint(Color.WHITE);

                g2D.fillRect(j*8, i*8, 8, 8);
            }
        }

        g2D.dispose();

        return image;
    }

    public static void setExitToImage(BufferedImage image, MazeData mazeData, Coords newCoords)
    {
        Graphics2D g2D = (Graphics2D) image.getGraphics();

        if(!mazeData.exit().equals(MazeData.Nowhere))
        {
            if(mazeData.maze()[mazeData.exit().y][mazeData.exit().x] == MazeData.Wall)
                g2D.setPaint(Color.BLACK);
            else
                g2D.setPaint(Color.WHITE);

            g2D.fillRect(mazeData.exit().x*8, mazeData.exit().y*8, 8, 8);
        }

        g2D.setPaint(Color.RED);
        g2D.fillRect(newCoords.x*8, newCoords.y*8, 8, 8);

        g2D.dispose();
    }

    public static void setEntryToImage(BufferedImage image, MazeData mazeData, Coords newCoords)
    {
        Graphics2D g2D = (Graphics2D) image.getGraphics();

        if(!mazeData.entry().equals(MazeData.Nowhere))
        {
            if(mazeData.maze()[mazeData.entry().y][mazeData.entry().x] == MazeData.Wall)
                g2D.setPaint(Color.BLACK);
            else
                g2D.setPaint(Color.WHITE);

            g2D.fillRect(mazeData.entry().x*8, mazeData.entry().y*8, 8, 8);
        }

        g2D.setPaint(Color.GREEN);
        g2D.fillRect(newCoords.x*8, newCoords.y*8, 8, 8);

        g2D.dispose();
    }

}
