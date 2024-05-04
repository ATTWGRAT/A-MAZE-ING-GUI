package AmazeingGui;

public record MazeData(int width, int height, int[][] maze) {
    public static final int Entry = -3;
    public static final int Exit = -2;
    public static final int Wall = -1;
    public static final int Path = 0;

    public void setExit(int x, int y) {
        try {
            maze[y][x] = Exit;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error setting the exit location in the maze: " + e.getMessage());
        }
    }

    public void setEntry(int x, int y) {
        try {
            maze[y][x] = Entry;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error setting the entry location in the maze: " + e.getMessage());
        }
    }
}
