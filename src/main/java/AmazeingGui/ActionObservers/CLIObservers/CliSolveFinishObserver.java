package AmazeingGui.ActionObservers.CLIObservers;

import AmazeingGui.CLI;
import AmazeingGui.MazeData.MazeDataSingleton;

public class CliSolveFinishObserver extends CliObserver{
    public CliSolveFinishObserver(CLI cli)
    {
        super(cli);
    }

    @Override
    public void call() {
        if(MazeDataSingleton.getInstance().isSolved())
            cli.printToStream("Znaleziono rozwiązanie labiryntu!");
        else
            cli.printToStream("Nie znaleziono rozwiązania labiryntu! Zmień pozycję wejścia / wyjścia.");

        cli.resetState();
    }
}
