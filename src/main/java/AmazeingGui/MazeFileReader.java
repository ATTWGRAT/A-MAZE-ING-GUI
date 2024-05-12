package AmazeingGui;

import AmazeingGui.Exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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

        width = line.length();

        ArrayList<int[]> maze = new ArrayList<>();

        int[] temp;

        while(line != null)
        {
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
                            throw new MazeDoubleEntryException("Znaleziono dwa wejścia w labiryncie!");
                        entry = new Coords(i, height-1);
                        temp[i] = MazeData.Path;
                        break;
                    case 'K':
                        if(exit.x != -1)
                            throw new MazeDoubleExitException("Znaleziono dwa wyjścia w labiryncie!");
                        exit = new Coords(i, height-1);
                        temp[i] = MazeData.Path;
                        break;
                    case ' ':
                        if(i == 0 || i == width - 1)
                            throw new MazeIncorrectCharException("Błędny znak w pliku w pozycji (" + i + ", " + height + ")");

                        temp[i] = MazeData.Path;
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
            if(maze.getFirst()[i] == 0 && entry.x != i && exit.x != i) {
                throw new MazeIncorrectCharException("Błędny znak w pliku w pozycji (" + i + ", " + 0 + ")");
            }
        }

        for (int i = 0; i < width; i++)
        {
            if(maze.getLast()[i] == 0 && entry.x != i && exit.x != i) {
                throw new MazeIncorrectCharException("Błędny znak w pliku w pozycji (" + i + ", " + 0 + ")");
            }
        }

        //Arraylist do 2d tablicy

        int[][] finalArray = new int[maze.size()][width];

        for (int i = 0; i < maze.size(); i++) {
            finalArray[i] = maze.get(i);
        }

        return new MazeData(finalArray, entry, exit);

    }
}
