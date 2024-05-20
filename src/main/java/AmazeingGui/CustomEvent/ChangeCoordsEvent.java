package AmazeingGui.CustomEvent;

import AmazeingGui.Coords;

public class ChangeCoordsEvent extends CustomEvent{
    private final Coords oldCoords;
    private final Coords newCoords;
    private final boolean isExit;

    public ChangeCoordsEvent(Coords oldCoords, Coords newCoords, boolean isExit) {
        this.oldCoords = oldCoords;
        this.newCoords = newCoords;
        this.isExit = isExit;
    }

    public Coords getOldCoords() {
        return oldCoords;
    }

    public Coords getNewCoords() {
        return newCoords;
    }

    public boolean isExit() {
        return isExit;
    }
}
