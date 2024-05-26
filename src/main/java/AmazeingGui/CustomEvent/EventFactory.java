package AmazeingGui.CustomEvent;

import AmazeingGui.Coords;
import AmazeingGui.MazeData;

public class EventFactory {
    public static CustomEvent createMazeChangeEvent(String source, MazeData newData)
    {
        return new MazeFileReadEvent(source, newData);
    }

    public static CustomEvent createCoordsChangeEvent(Coords oldCoords, Coords newCoords, boolean isExit)
    {
        return new ChangeCoordsEvent(oldCoords, newCoords, isExit);
    }
}
