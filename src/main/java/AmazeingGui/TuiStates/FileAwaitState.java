package AmazeingGui.TuiStates;

import AmazeingGui.CustomEvent.EventFactory;
import AmazeingGui.CustomEvent.EventType;
import AmazeingGui.CustomEventManager;
import AmazeingGui.Exceptions.MazeException;
import AmazeingGui.MazeData;
import AmazeingGui.MazeFileReader;
import AmazeingGui.CLI;

import java.io.File;
import java.io.IOException;

public class FileAwaitState implements CliState {
    private CLI cli;

    public FileAwaitState(CLI cli)
    {
        this.cli = cli;
        cli.printToStream("Wybierz plik: ");
    }

    @Override
    public boolean parseAndExecute(String str) {
        File file = new File(str);
        try
        {
            MazeData data = MazeFileReader.readTxtToMazeData(file);

            CustomEventManager.getInstance().callEvent(
                    EventType.fileReadEvent,
                    EventFactory.createMazeChangeEvent(str, data)
            );

        }
        catch (IOException e)
        {
            cli.printToStream("Błąd podczas czytania z pliku: " + e.getMessage());
            cli.changeState(new FileAwaitState(cli));
        }
        catch (MazeException e)
        {
            cli.printToStream("Błąd podczas odczytywania labiryntu: " + e.getMessage());
            cli.changeState(new FileAwaitState(cli));
        }
        return false;
    }
}
