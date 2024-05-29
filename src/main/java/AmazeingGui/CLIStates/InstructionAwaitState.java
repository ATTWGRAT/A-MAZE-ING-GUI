package AmazeingGui.CLIStates;

import AmazeingGui.CLI;

public class InstructionAwaitState implements CliState {
    private CLI cli;
    private boolean noExit;

    public InstructionAwaitState(CLI cli, boolean noExit)
    {
        this.cli = cli;
        this.noExit = noExit;
        String out = "(F) Wczytaj labirynt | (W) Wypisz labirynt | (EX) Wybierz wyjście | (EN) Wybierz wejście";

        if(!noExit)
            out += " | (S) Rozwiąż";

        cli.printToStream(out);
    }

    @Override
    public void parseAndExecute(String str) {
        switch(str.toUpperCase())
        {
            case "F":
                cli.changeState(new FileAwaitState(cli));
                break;
            default:
                cli.printToStream("Błędna komenda!");
                cli.changeState(new InstructionAwaitState(cli, noExit));
        }
    }


}
