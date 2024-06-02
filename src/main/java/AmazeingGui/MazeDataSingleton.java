package AmazeingGui;

import java.util.*;

public final class MazeDataSingleton
{
    public static final byte Route = -2;
    public static final byte Wall = -1;
    public static final byte Path = 0;

    private String source;
    private byte[][] maze;
    private Coords entry;
    private Coords exit;
    private boolean isSolved;

    private static MazeDataSingleton instance;

    private MazeDataSingleton()
    {
        byte[][] fill = new byte[1000][1000];

        for(byte[] row : fill)
            Arrays.fill(row, (byte) 0);

        maze = fill;
        isSolved = false;
    }

    static void initialize()
    {
        instance = new MazeDataSingleton();
    }

    public synchronized void changeMaze(byte[][] maze, Coords entry, Coords exit, String source) {
        this.maze = maze;
        this.entry = entry;
        this.exit = exit;
        this.source = source;
        this.isSolved = false;
    }

    private synchronized void clearRoute()
    {
        for(byte[] arr : maze)
            for(int i = 0; i < width(); i++)
                if(arr[i] == Route)
                    arr[i] = Path;

        isSolved = false;
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
        clearRoute();
        if(newCoords.equals(entry))
            entry = null;

        exit = newCoords;
    }

    public synchronized void setEntry(Coords newCoords) {
        if(newCoords.x >= width() || newCoords.y >= height()) {
            System.err.println("Error setting the exit location in the maze: " + newCoords.x + ", " + newCoords.y);
            return;
        }
        clearRoute();
        if(newCoords.equals(exit))
            exit = null;

        entry = newCoords;
    }

    public synchronized byte[][] getMaze() {
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

    public synchronized void solve()
    {
        if(entry == null || exit == null)
            throw new RuntimeException("Cannot start solving while entrance / exit is null!");

        Thread thread = new Thread(this::insideSolve);
        thread.start();
    }

    private synchronized void insideSolve()
    {
        int[][] intMaze = new int[maze.length][maze[0].length];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if(maze[i][j] == 0 || (entry.x == j && entry.y == i) || (exit.x == j && exit.y == i))
                    intMaze[i][j] = Integer.MAX_VALUE;
                else
                    intMaze[i][j] = -1;
            }
        }

        boolean solveFlag = false;

        DJQueue djQueue = new DJQueue();

        djQueue.addToQueue(entry, 0);
        intMaze[entry.y][entry.x] = 0;

        while(!djQueue.isEmpty())
        {
            int currLen = djQueue.peekLength();
            Coords currCoords = djQueue.popCoords();

            if(currCoords.equals(exit))
            {
                solveFlag = true;
                break;
            }

            int x = currCoords.x;
            int y = currCoords.y;

            if(x > 0){
                if(intMaze[y][x-1] > currLen + 1) {
                    djQueue.addToQueue(new Coords(x - 1, y), currLen + 1);
                    intMaze[y][x-1] = currLen + 1;
                }
            }
            if(x < width() - 1){
                if(intMaze[y][x+1] > currLen + 1) {
                    djQueue.addToQueue(new Coords(x + 1, y), currLen + 1);
                    intMaze[y][x+1] = currLen + 1;
                }
            }
            if(y > 0){
                if(intMaze[y-1][x] > currLen + 1) {
                    djQueue.addToQueue(new Coords(x, y - 1), currLen + 1);
                    intMaze[y-1][x] = currLen + 1;
                }
            }
            if(y < height() - 1){
                if(intMaze[y+1][x] > currLen + 1) {
                    djQueue.addToQueue(new Coords(x, y + 1), currLen + 1);
                    intMaze[y+1][x] = currLen + 1;
                }
            }
        }

        if(!solveFlag) {
            CustomEventManager.getInstance().callEvent(EventType.solveFinishEvent);
            return;
        }
        Coords currCoords = exit;
        int currLen = intMaze[exit.y][exit.x];

        while(currLen > 1)
        {
            currLen--;

            int x = currCoords.x;
            int y = currCoords.y;

            if(x > 0){
                if(intMaze[y][x-1] == currLen) {
                    maze[y][x-1] = -2;
                    currCoords = new Coords(x-1, y);
                    continue;
                }
            }
            if(x < width() - 1){
                if(intMaze[y][x+1] == currLen) {
                    maze[y][x+1] = -2;
                    currCoords = new Coords(x+1, y);
                    continue;
                }
            }
            if(y > 0){
                if(intMaze[y-1][x] == currLen) {
                    maze[y-1][x] = -2;
                    currCoords = new Coords(x, y-1);
                    continue;
                }
            }
            if(y < height() - 1){
                if(intMaze[y+1][x] == currLen) {
                    maze[y+1][x] = -2;
                    currCoords = new Coords(x, y+1);
                    continue;
                }
            }
            throw new RuntimeException("Cannot backtrack a route after finding a solution! THIS SHOULD NOT HAPPEN AND MEANS THERE IS AN ERROR IN THE ALGORITHM");

        }

        isSolved = true;
        CustomEventManager.getInstance().callEvent(EventType.solveFinishEvent);

    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solveFlag) {
        isSolved = solveFlag;
    }
}