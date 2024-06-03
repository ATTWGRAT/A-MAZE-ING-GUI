package AmazeingGui.CLIStates;

import AmazeingGui.*;
import AmazeingGui.MazeData.Coords;
import AmazeingGui.MazeData.MazeDataSingleton;

public class ChangeEntryState implements CliState{
    private CLI cli;
    private Coords newCoords;

    public ChangeEntryState(CLI cli) {
        this.cli = cli;
        newCoords = new Coords(-1, -1);
        cli.printToStream("Podaj nowy koordynat x wejścia: ");
    }

    @Override
    public void parseAndExecute(String str) {
        try {
            if (newCoords.x == -1) {
                newCoords.x = Integer.parseInt(str);

                if(newCoords.x >= MazeDataSingleton.getInstance().width()) {
                    cli.printToStream("Podana liczba jest zbyt duża! Max: " + (MazeDataSingleton.getInstance().width() - 1));
                    newCoords.x = -1;
                    return;
                }

                cli.printToStream("Podaj nowy koordynat y wejścia: ");
            }
            else
            {
                newCoords.y = Integer.parseInt(str);

                if(newCoords.y >= MazeDataSingleton.getInstance().height()) {
                    cli.printToStream("Podana liczba jest zbyt duża! Max: " + (MazeDataSingleton.getInstance().height() - 1));
                    newCoords.y = -1;
                    return;
                }

                MazeDataSingleton.getInstance().setEntry(newCoords);
                CustomEventManager.getInstance().callEvent(EventType.entryChangeEvent);
            }
        } catch (NumberFormatException e)
        {
            cli.printToStream("Musisz podać liczbę!");
        }
    }
}
