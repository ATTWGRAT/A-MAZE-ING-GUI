package AmazeingGui;

import AmazeingGui.Exceptions.*;

import java.io.*;
import java.util.ArrayList;

public class MazeFileReader {
    public static boolean isFileBinary(File file) throws IOException{
        InputStream inputStream = new FileInputStream(file);

        byte[] buffer = new byte[4];

        if(inputStream.read(buffer) != 4)
            return false;

        inputStream.close();

        return buffer[3] == 0x52
                && buffer[2] == 0x52
                && buffer[1] == 0x42
                && buffer[0] == 0x43;

    }

    public static MazeData readTxtToMazeData(File file) throws IOException, MazeException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Coords entry = new Coords(-1, -1);
        Coords exit = new Coords(-1, -1);
        int height = 0;
        int width;
        String line;

        //Check first line
        line = reader.readLine();

        if(line == null)
            return null;

        height++;
        width = line.length();

        ArrayList<int[]> maze = new ArrayList<>();

        int[] temp = new int[width];

        for(int i = 0; i < line.length(); i++)
        {
            switch(line.charAt(i))
            {
                case 'X':
                    temp[i] = MazeData.Wall;
                    break;
                case 'P':
                    if(entry.x != -1)
                        throw new MazeDoubleEntryException("Znaleziono dwa wejscia w labiryncie!");
                    entry = new Coords(i, 0);
                    temp[i] = MazeData.Path;
                    break;
                case 'K':
                    if(exit.x != -1)
                        throw new MazeDoubleExitException("Znaleziono dwa wyjscia w labiryncie!");
                    exit = new Coords(i, 0);
                    temp[i] = MazeData.Path;
                    break;
                default:
                    throw new MazeIncorrectCharException("Błędny znak w pliku w pozycji (" + i + ", 0): " + line.charAt(i));
            }
        }

        maze.add(temp);

        //Reszta labiryntu z wyjątkiem ostatniej linii
        String templine;

        while((templine = reader.readLine()) != null)
        {
            line = templine;
            temp = new int[width];

            height++;

            if(line.length() != width)
                throw new MazeInconsistentWidthException("Błędna długość wiersza w linii " + height);

            for(int i = 0; i < line.length(); i++)
            {
                switch(line.charAt(i))
                {
                    case 'X':
                        temp[i] = MazeData.Wall;
                        break;
                    case 'P':
                        if(entry.x != -1)
                            throw new MazeDoubleEntryException("Znaleziono dwa wejscia w labiryncie!");
                        entry = new Coords(i, height-1);
                        temp[i] = MazeData.Path;
                        break;
                    case 'K':
                        if(exit.x != -1)
                            throw new MazeDoubleExitException("Znaleziono dwa wyjscia w labiryncie!");
                        exit = new Coords(i, height-1);
                    case ' ':
                        temp[i] = MazeData.Path;
                        break;

                    default:
                        throw new MazeIncorrectCharException("Błędny znak w pliku w pozycji (" + i + ", " + height + "): " + line.charAt(i));
                }
            }

            maze.add(temp);
        }

        //Sprawdzenie ostatniej linii

        if(line.contains(" ")) {
            int i = line.indexOf(' ');
            throw new MazeIncorrectCharException("Błędny znak w pliku w pozycji (" + i + ", " + height + ")");
        }

        reader.close();

        //Arraylist do 2d tablicy

        int[][] finalArray = new int[maze.size()][width];

        for (int i = 0; i < maze.size(); i++) {
            finalArray[i] = maze.get(i);
        }

        return new MazeData(width, height, finalArray, entry, exit);

    }
}
