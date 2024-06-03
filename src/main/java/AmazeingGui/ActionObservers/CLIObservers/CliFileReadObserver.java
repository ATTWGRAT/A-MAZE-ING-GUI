package AmazeingGui.ActionObservers.CLIObservers;

import AmazeingGui.CLI;
import AmazeingGui.MazeData.MazeDataSingleton;
import AmazeingGui.CLIStates.InstructionAwaitState;

public class CliFileReadObserver extends CliObserver {
    public CliFileReadObserver(CLI cli)
    {
        super(cli);
    }

    @Override
    public void call() {
        cli.printToStream("Wczytano nowy labirynt z pliku: " + MazeDataSingleton.getInstance().getSource());

        if(MazeDataSingleton.getInstance().getEntry() != null && MazeDataSingleton.getInstance().getExit() != null)
            cli.setSolveable(true);

        cli.changeState(new InstructionAwaitState(cli));

    }
}
