package AmazeingGui.ActionObservers.CLIObservers;

import AmazeingGui.CLI;
import AmazeingGui.CLIStates.SolveAwaitState;

public class CliSolveBeginObserver extends CliObserver{
    public CliSolveBeginObserver(CLI cli)
    {
        super(cli);
    }

    @Override
    public void call() {
        cli.printToStream("Rozpoczęto rozwiazywanie labiryntu");
        cli.changeState(new SolveAwaitState(cli));
    }
}
