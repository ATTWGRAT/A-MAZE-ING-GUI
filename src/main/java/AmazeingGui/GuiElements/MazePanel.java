package AmazeingGui.GuiElements;

import AmazeingGui.MazeData;

import javax.swing.*;
import java.awt.*;

public class MazePanel {
    private MazeView mazeView;
    private JScrollPane scrollPane;

    public MazePanel()
    {
        int[][] maze = new int[1000][1000];

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++)
            {
                if((i + j)%2 == 1)
                    maze[i][j] = MazeData.FieldTypes.Wall.value;
                else
                    maze[i][j] = MazeData.FieldTypes.Path.value;
            }
        }

        MazeData tempData = new MazeData(1000, 1000, maze);

        mazeView = new MazeView(tempData);

        scrollPane = new JScrollPane(mazeView);
        scrollPane.setAutoscrolls(true);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
