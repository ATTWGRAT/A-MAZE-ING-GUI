package AmazeingGui.CLIStates;

import AmazeingGui.CLI;
import AmazeingGui.FileIO.MazeFileWriter;

import java.io.File;
import java.io.IOException;

public class FileWriteState implements CliState{
    private final CLI cli;
    private Boolean isBin;

    public FileWriteState(CLI cli)
    {
        isBin = null;
        this.cli = cli;
        cli.printToStream("Podaj typ pliku (BIN | TXT):");
    }

    @Override
    public void parseAndExecute(String str) {
        if(isBin == null) {
            if (str.equalsIgnoreCase("TXT")) {
                isBin = false;
                cli.printToStream("Podaj nazwę pliku: ");
            }
            else if (str.equalsIgnoreCase("BIN")) {
                isBin = true;
                cli.printToStream("Podaj nazwę pliku: ");
            }
            else
                cli.printToStream("Błędna opcja!");
            return;
        }

        File file = new File(str);

        try
        {
            if(isBin)
            {
                try
                {
                    MazeFileWriter.writeMazeToBin(file);
                }
                catch(RuntimeException e)
                {
                    cli.printToStream("Nie można stworzyć pliku binarnego bez wyjścia / wejścia w labiryncie");
                    cli.resetState();
                    return;
                }
            } else {
                MazeFileWriter.writeMazeToTxt(file);
                cli.printToStream("Wypisano labirynt do: " + file.getName());
            }
        }
        catch(IOException e)
        {
            cli.printToStream("Błąd podczas wypisywania pliku: " + e.getMessage());
        }

        cli.resetState();
    }
}
