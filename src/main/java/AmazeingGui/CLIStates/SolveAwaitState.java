package AmazeingGui.CLIStates;

import AmazeingGui.CLI;

public class SolveAwaitState implements CliState{
    private final CLI cli;

    public SolveAwaitState(CLI cli)
    {
        this.cli = cli;
    }


    @Override
    public void parseAndExecute(String str) {
        cli.printToStream("Proszę zaczekać na zakończenie rozwiązywania!");
    }
}
