package AmazeingGui;

public class MazeData {
    public static final int Entry = -3;
    public static final int Exit = -2;
    public static final int Wall = -1;
    public static final int Path = 0;

    private final int width;
    private final int height;
    private final int[][] maze;

    public MazeData(int width, int height, int[][] maze) {
        this.width = width;
        this.height = height;
        this.maze = maze;
    }

    public void setExit(int x, int y)
    {
        try
        {
            maze[y][x] = Exit;
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println("Error setting the exit location in the maze: " + e.getMessage());
        }
    }

    public void setEntry(int x, int y)
    {
        try
        {
            maze[y][x] = Entry;
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println("Error setting the entry location in the maze: " + e.getMessage());
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMaze() {
        return maze;
    }
}
