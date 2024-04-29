package AmazeingGui.GuiElements;

import AmazeingGui.MazeData;

import javax.swing.*;

public class MazePanelComposite {
    private MazeView mazeView;
    private final JScrollPane scrollPane;

    public MazePanelComposite()
    {
        //temp for testing
        int[][] maze = new int[1000][1000];

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++)
            {
                if((i + j)%2 == 1)
                    maze[i][j] = MazeData.Wall;
                else
                    maze[i][j] = MazeData.Path;
            }
        }

        maze[1][0] = MazeData.Entry;
        maze[999][998] = MazeData.Exit;

        MazeData tempData = new MazeData(1000, 1000, maze);

        mazeView = new MazeView(tempData);

        scrollPane = new JScrollPane(mazeView.getMazeViewPanel());
        scrollPane.setAutoscrolls(true);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void changeMazeData(MazeData mazeData)
    {
        mazeView = new MazeView(mazeData);
        scrollPane.setViewportView(mazeView.getMazeViewPanel());
    }
}
