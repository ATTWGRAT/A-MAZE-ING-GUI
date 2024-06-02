package AmazeingGui.ActionObservers.CLIObservers;

import AmazeingGui.CLI;
import AmazeingGui.MazeDataSingleton;

public class CliChangeEntryObserver extends CliObserver{
    public CliChangeEntryObserver(CLI cli)
    {
        super(cli);
    }

    @Override
    public void call() {
        cli.printToStream("Zmieniono koordynaty wejścia. Nowe koordynaty: " + MazeDataSingleton.getInstance().getEntry());

        cli.setSolveable(MazeDataSingleton.getInstance().getEntry() != null && MazeDataSingleton.getInstance().getExit() != null);

        cli.resetState();
    }
}
