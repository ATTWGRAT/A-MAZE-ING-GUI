package AmazeingGui;

public record MazeData(int[][] maze, Coords entry, Coords exit) {
    public static final int Wall = -1;
    public static final int Path = 0;
    public static final Coords Nowhere = new Coords(-1, -1);

    public int width() {
        return maze[0].length;
    }

    public int height() {
        return maze.length;
    }

    public void setExit(Coords newCoords) {
        if(newCoords.x >= width() || newCoords.y >= height())
            System.err.println("Error setting the exit location in the maze: " + newCoords.x + ", " + newCoords.y);

        exit.x = newCoords.x;
        exit.y = newCoords.y;
    }

    public void setEntry(Coords newCoords) {
        if(newCoords.x >= width() || newCoords.y >= height())
            System.err.println("Error setting the exit location in the maze: " + newCoords.x + ", " + newCoords.y);

        entry.x = newCoords.x;
        entry.y = newCoords.y;
    }
}
