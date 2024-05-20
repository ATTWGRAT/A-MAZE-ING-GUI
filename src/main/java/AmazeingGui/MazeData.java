package AmazeingGui;

public class MazeData
{
    public static final int Wall = -1;
    public static final int Path = 0;

    private final int[][] maze;
    private Coords entry;
    private Coords exit;

    public MazeData(int[][] maze, Coords entry, Coords exit) {
        this.maze = maze;
        this.entry = entry;
        this.exit = exit;
    }

    public int width() {
        return maze[0].length;
    }

    public int height() {
        return maze.length;
    }

    public synchronized void setExit(Coords newCoords) {
        if(newCoords.x >= width() || newCoords.y >= height())
            System.err.println("Error setting the exit location in the maze: " + newCoords.x + ", " + newCoords.y);

        exit = newCoords;
    }

    public synchronized void setEntry(Coords newCoords) {
        if(newCoords.x >= width() || newCoords.y >= height())
            System.err.println("Error setting the exit location in the maze: " + newCoords.x + ", " + newCoords.y);

        entry = newCoords;
    }

    public int[][] getMaze() {
        return maze;
    }

    public Coords getEntry() {
        return entry;
    }

    public Coords getExit() {
        return exit;
    }
}
