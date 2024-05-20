package AmazeingGui.CustomEvent;

import AmazeingGui.MazeData;

public class MazeFileReadEvent extends CustomEvent{
    private final String source;
    private final MazeData newMaze;

    MazeFileReadEvent(String source , MazeData newMaze) {
        this.source = source;
        this.newMaze = newMaze;
    }

    public String getSource() {
        return source;
    }

    public MazeData getNewMaze() {
        return newMaze;
    }
}
