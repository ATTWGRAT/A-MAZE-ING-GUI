package AmazeingGui.CLIStates;

import AmazeingGui.CLI;
import AmazeingGui.MazeFileWriter;

import java.io.File;
import java.io.IOException;

public class FileWriteState implements CliState{
    private final CLI cli;

    public FileWriteState(CLI cli)
    {
        this.cli = cli;
        cli.printToStream("Podaj nazwę pliku: ");
    }

    @Override
    public void parseAndExecute(String str) {
        File file = new File(str);

        try
        {
            MazeFileWriter.writeMazeToTxt(file);
            cli.printToStream("Wypisano labirynt do: " + file.getName());
        }
        catch(IOException e)
        {
            cli.printToStream("Błąd podczas wypisywania pliku: " + e.getMessage());
        }

        cli.resetState();
    }
}
