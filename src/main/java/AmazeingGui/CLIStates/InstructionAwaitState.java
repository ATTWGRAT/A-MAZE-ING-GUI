package AmazeingGui.CLIStates;

import AmazeingGui.CLI;

public class InstructionAwaitState implements CliState {
    private CLI cli;

    public InstructionAwaitState(CLI cli)
    {
        this.cli = cli;
        boolean isSolveable = cli.isSolveable();

        String out = "(F) Wczytaj labirynt | (W) Wypisz labirynt | (EX) Wybierz wyjście | (EN) Wybierz wejście";

        if(isSolveable)
            out += " | (S) Rozwiąż";
        else
            cli.printToStream("Aby móc rozwiązać labirynt musisz w nim dodać wejście i wyjście!");

        cli.printToStream(out);
    }

    @Override
    public void parseAndExecute(String str) {
        switch(str.toUpperCase())
        {
            case "F":
                cli.changeState(new FileAwaitState(cli));
                break;
            case "EX":
                cli.changeState(new ChangeExitState(cli));
                break;
            case "EN":
                cli.changeState(new ChangeEntryState(cli));
                break;
            default:
                cli.printToStream("Błędna komenda!");
                cli.changeState(new InstructionAwaitState(cli));
        }
    }


}
