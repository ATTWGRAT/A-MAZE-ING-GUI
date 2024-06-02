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

    public synchronized static void readTxtToMazeData(File file) throws IOException, MazeException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Coords entry = null;
        Coords exit = null;
        int height = 0;
        int width;
        String line;

        //Check first line
        line = reader.readLine();

        if(line == null)
            throw new MazeTooSmallException("Podano pusty plik!");

        width = line.length();

        ArrayList<byte[]> maze = new ArrayList<>();

        byte[] temp;

        while(line != null)
        {
            temp = new byte[width];

            height++;

            if(line.length() != width)
                throw new MazeInconsistentWidthException("Błędna długość wiersza w linii " + height);

            for(int i = 0; i < line.length(); i++)
            {
                switch(line.charAt(i))
                {
                    case 'X':
                        temp[i] = MazeDataSingleton.Wall;
                        break;

                    case 'P':
                        if(entry != null)
                            throw new MazeDoubleEntryException("Znaleziono dwa wejścia w labiryncie!");

                        entry = new Coords(i, height-1);

                        if(i == 0 || i == width - 1)
                            temp[i] = MazeDataSingleton.Wall;
                        else
                            temp[i] = MazeDataSingleton.Path;

                        break;

                    case 'K':
                        if(exit != null)
                            throw new MazeDoubleExitException("Znaleziono dwa wyjścia w labiryncie!");

                        exit = new Coords(i, height-1);

                        if(i == 0 || i == width - 1)
                            temp[i] = MazeDataSingleton.Wall;
                        else
                            temp[i] = MazeDataSingleton.Path;

                        break;

                    case ' ':
                        if(i == 0 || i == width - 1)
                            throw new MazeIncorrectCharException("Błędny znak w pliku w pozycji (" + i + ", " + height + ")");

                        temp[i] = MazeDataSingleton.Path;

                        break;

                    default:
                        throw new MazeIncorrectCharException("Błędny znak w pliku w pozycji (" + i + ", " + height + "): " + line.charAt(i));
                }
            }

            maze.add(temp);

            line = reader.readLine();
        }

        reader.close();

        if(height < 3 || width < 3)
            throw new MazeTooSmallException("Rozmiar labiryntu jest za mały! (" + width + "x" + height + ")");

        //Sprawdzenie ostatniej i pierwszej linii

        for (int i = 0; i < width; i++)
        {
            if(maze.getFirst()[i] == 0) {
                if((entry == null || entry.x != i) && (exit == null || exit.x != i))
                    throw new MazeIncorrectCharException("Błędny znak w pliku w pozycji (" + i + ", " + 0 + ")");
            }
        }

        for (int i = 0; i < width; i++)
        {
            if(maze.getLast()[i] == 0 ) {
                if((entry == null || entry.x != i) && (exit == null || exit.x != i))
                    throw new MazeIncorrectCharException("Błędny znak w pliku w pozycji (" + i + ", " + 0 + ")");
            }
        }

        //Arraylist do 2d tablicy

        byte[][] finalArray = new byte[maze.size()][width];

        for (int i = 0; i < maze.size(); i++) {
            finalArray[i] = maze.get(i);
        }

        MazeDataSingleton.getInstance().changeMaze(finalArray, entry, exit, file.getName());
    }
}
