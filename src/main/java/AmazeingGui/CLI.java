package AmazeingGui;

import AmazeingGui.CustomActionListeners.TuiListeners.CliFileReadListener;
import AmazeingGui.CustomEvent.EventType;
import AmazeingGui.TuiStates.FileAwaitState;
import AmazeingGui.TuiStates.CliState;

import java.io.PrintStream;
import java.util.Scanner;

public final class CLI {
    private MazeData mazeData;
    private PrintStream stream;
    private volatile CliState state;

    public CLI()
    {
        CustomEventManager.getInstance().registerListener(EventType.fileReadEvent, new CliFileReadListener(this));

        stream = System.out;

        System.out.println("Uruchomiono program A-MAZE-ING!");

        state = new FileAwaitState(this);

        runParser();

    }

    public CLI(String path)
    {
        CustomEventManager.getInstance().registerListener(EventType.fileReadEvent, new CliFileReadListener(this));

        stream = System.out;

        System.out.println("Uruchomiono program A-MAZE-ING!");

        state = new FileAwaitState(this);

        state.parseAndExecute(path);

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

    public void setMazeData(MazeData mazeData)
    {
        this.mazeData = mazeData;
    }

}
