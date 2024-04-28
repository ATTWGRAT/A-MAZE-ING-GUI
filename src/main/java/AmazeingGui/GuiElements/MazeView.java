package AmazeingGui.GuiElements;

import AmazeingGui.MazeData;

import javax.swing.*;
import java.awt.*;

public class MazeView extends JPanel {
    private MazeData mazeData;
    public MazeView(MazeData mazeData)
    {
        super();
        this.mazeData = mazeData;
        setPreferredSize(new Dimension(5*mazeData.getWidth(), 5*mazeData.getHeight()));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        for (int i = 0; i < mazeData.getHeight(); i++) {
            for (int j = 0; j < mazeData.getWidth(); j++)
            {
                if(mazeData.getMaze()[i][j] == MazeData.FieldTypes.Wall.value)
                {
                    g2D.setPaint(Color.BLACK);
                }
                else
                {
                    g2D.setPaint(Color.WHITE);
                }

                g2D.fillRect(j*10, i*10, 10, 10);
            }
        }

    }
}
