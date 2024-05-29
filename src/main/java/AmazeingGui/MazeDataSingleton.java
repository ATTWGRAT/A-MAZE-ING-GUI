package AmazeingGui;

import java.util.Arrays;

public final class MazeDataSingleton
{
    public static final int Wall = -1;
    public static final int Path = 0;

    private String source;
    private int[][] maze;
    private Coords entry;
    private Coords exit;

    private static MazeDataSingleton instance;

    private MazeDataSingleton()
    {
        int[][] fill = new int[1000][1000];

        for(int[] row : fill)
            Arrays.fill(row, 0);

        maze = fill;
    }

    static void initialize()
    {
        instance = new MazeDataSingleton();
    }

    public synchronized void changeMaze(int[][] maze, Coords entry, Coords exit, String source) {
        this.maze = maze;
        this.entry = entry;
        this.exit = exit;
        this.source = source;
    }

    public static MazeDataSingleton getInstance() {
        return instance;
    }

    public synchronized int width() {
        return maze[0].length;
    }

    public synchronized int height() {
        return maze.length;
    }

    public synchronized void setExit(Coords newCoords) {
        if(newCoords.x >= width() || newCoords.y >= height()) {
            System.err.println("Error setting the exit location in the maze: " + newCoords.x + ", " + newCoords.y);
            return;
        }
        exit = newCoords;
    }

    public synchronized void setEntry(Coords newCoords) {
        if(newCoords.x >= width() || newCoords.y >= height()) {
            System.err.println("Error setting the exit location in the maze: " + newCoords.x + ", " + newCoords.y);
            return;
        }
        entry = newCoords;
    }

    public synchronized int[][] getMaze() {
        return maze;
    }

    public synchronized Coords getEntry() {
        return entry;
    }

    public synchronized Coords getExit() {
        return exit;
    }

    public synchronized String getSource()
    {
        return source;
    }
}
