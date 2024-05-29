package AmazeingGui.ActionObservers.CLIObservers;

import AmazeingGui.CLI;
import AmazeingGui.MazeDataSingleton;
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
            cli.changeState(new InstructionAwaitState(cli, false));
        else
            cli.changeState(new InstructionAwaitState(cli, true));

    }
}
