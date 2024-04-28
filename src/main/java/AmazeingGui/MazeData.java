package AmazeingGui;

public class MazeData {
    public enum FieldTypes{
        Entry(-1),
        Exit(-2),
        Wall(-3),
        Path(0);

        public final int value;
        FieldTypes(int value)
        {
            this.value = value;
        }
    }

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
            maze[y][x] = FieldTypes.Exit.ordinal();
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
            maze[y][x] = FieldTypes.Entry.ordinal();
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
