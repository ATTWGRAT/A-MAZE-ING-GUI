package AmazeingGui.ActionObservers.CLIObservers;

import AmazeingGui.CLI;
import AmazeingGui.MazeDataSingleton;

public class CliChangeExitObserver extends CliObserver {
    public CliChangeExitObserver(CLI cli)
    {
        super(cli);
    }

    @Override
    public void call() {
        cli.printToStream("Zmieniono koordynaty wyjścia. Nowe koordynaty: " + MazeDataSingleton.getInstance().getExit());

        cli.setSolveable(MazeDataSingleton.getInstance().getEntry() != null && MazeDataSingleton.getInstance().getExit() != null);

        cli.resetState();
    }
}
