package AmazeingGui;

import AmazeingGui.ActionObservers.CLIObservers.*;
import AmazeingGui.CLIStates.FileAwaitState;
import AmazeingGui.CLIStates.CliState;
import AmazeingGui.CLIStates.InstructionAwaitState;

import java.io.PrintStream;
import java.util.Scanner;

public final class CLI {
    private PrintStream stream;
    private volatile CliState state;
    private boolean isSolveable;

    private void setup()
    {
        CustomEventManager.getInstance().registerObserver(EventType.fileReadEvent, new CliFileReadObserver(this));
        CustomEventManager.getInstance().registerObserver(EventType.entryChangeEvent, new CliChangeEntryObserver(this));
        CustomEventManager.getInstance().registerObserver(EventType.exitChangeEvent, new CliChangeExitObserver(this));
        CustomEventManager.getInstance().registerObserver(EventType.solveBeginEvent, new CliSolveBeginObserver(this));
        CustomEventManager.getInstance().registerObserver(EventType.solveFinishEvent, new CliSolveFinishObserver(this));

        isSolveable = false;
        stream = System.out; //Potentially changeable

        System.out.println("Uruchomiono program A-MAZE-ING!");

        state = new FileAwaitState(this);

    }

    public CLI()
    {
        setup();

        runParser();

    }

    public CLI(String path)
    {
        setup();

        state.parseAndExecute(path);

        if(isSolveable)
            state.parseAndExecute("S");

        runParser();

    }

    private void runParser()
    {
        Scanner scanner = new Scanner(System.in);
        String currentLine;

        while(true)
        {
            currentLine = scanner.nextLine();
            state.parseAndExecute(currentLine);
        }
    }

    public void printToStream(String str)
    {
        stream.println(str);
    }

    public void changeState(CliState state)
    {
        this.state = state;
    }

    public boolean isSolveable() {
        return isSolveable;
    }

    public void setSolveable(boolean solveable) {
        isSolveable = solveable;
    }

    public void resetState()
    {
        this.state = new InstructionAwaitState(this);
    }
}
