package AmazeingGui.CLIStates;

import AmazeingGui.CLI;
import AmazeingGui.CustomEventManager;
import AmazeingGui.EventType;
import AmazeingGui.MazeDataSingleton;

public class InstructionAwaitState implements CliState {
    private final CLI cli;

    public InstructionAwaitState(CLI cli)
    {
        this.cli = cli;
        boolean isSolveable = cli.isSolveable();

        String out = "(F) Wczytaj labirynt | (W) Wypisz labirynt | (EX) Wybierz wyjście | (EN) Wybierz wejście";

        if(isSolveable)
            out += " | (S) Rozwiąż";
        else
            cli.printToStream("Aby móc rozwiązać labirynt musisz w nim dodać wejście i wyjście!");

        out += " | (X) Wyjdź";

        cli.printToStream(out);
    }

    @Override
    public void parseAndExecute(String str) {
        switch(str.toUpperCase())
        {
            case "F":
                cli.changeState(new FileAwaitState(cli));
                break;
            case "W":
                cli.changeState(new FileWriteState(cli));
                break;
            case "EX":
                cli.changeState(new ChangeExitState(cli));
                break;
            case "EN":
                cli.changeState(new ChangeEntryState(cli));
                break;
            case "S":
                if(!cli.isSolveable()) {
                    cli.printToStream("Błędna komenda!");
                    cli.resetState();
                }
                CustomEventManager.getInstance().callEvent(EventType.solveBeginEvent);
                MazeDataSingleton.getInstance().solve();
                break;
            case "X":
                System.exit(0);
            default:
                cli.printToStream("Błędna komenda!");
                cli.resetState();
        }
    }


}
