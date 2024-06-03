package AmazeingGui.FileIO;

import AmazeingGui.MazeData.MazeDataSingleton;

import java.io.*;
import java.util.ArrayList;

public class MazeFileWriter {
    public static synchronized void writeMazeToTxt(File file) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        MazeDataSingleton data = MazeDataSingleton.getInstance();

        for (int i = 0; i < data.height(); i++)
        {
            byte[] row = data.getMaze()[i];
            char[] buffer = new char[row.length + 1];

            for (int j = 0; j < row.length; j++)
            {
                if(data.getEntry() != null && data.getEntry().y == i && data.getEntry().x == j)
                    buffer[j] = 'P';
                else if (data.getExit() != null && data.getExit().y == i && data.getExit().x == j)
                    buffer[j] = 'K';
                else {
                    switch (row[j]) {
                        case MazeDataSingleton.Path -> buffer[j] = ' ';
                        case MazeDataSingleton.Wall -> buffer[j] = 'X';
                        case MazeDataSingleton.Route -> buffer[j] = 'R';
                        default -> throw new RuntimeException("Błędny element w labiryncie!");
                    }
                }
            }

            buffer[row.length] = '\n';

            writer.write(buffer);
        }

        writer.close();
    }

    public static synchronized void writeMazeToBin(File file) throws IOException
    {
        BinaryFileHeader header = new BinaryFileHeader(MazeDataSingleton.getInstance());

        RandomAccessFile raf = new RandomAccessFile(file, "rw");

        MazeDataSingleton data = MazeDataSingleton.getInstance();

        ArrayList<Byte> byteBuffer = new ArrayList<>();

        int currWordNr = 0;
        int wordLength = 0;
        byte wordSymbol;
        byte currWord = -3;

        for(int i = 0; i < data.height(); i++)
        {
            for(int j = 0; j < data.width(); j++)
            {
                if((data.getEntry().x == j && data.getEntry().y == i) || (data.getExit().x == j && data.getExit().y == i))
                    wordSymbol = header.path;
                else
                    wordSymbol = switch (data.getMaze()[i][j]) {
                        case MazeDataSingleton.Route, MazeDataSingleton.Path -> header.path;
                        case MazeDataSingleton.Wall -> header.wall;
                        default -> throw new RuntimeException("Błędny element w labiryncie!");
                    };

                if(wordSymbol != currWord)
                {
                    if(wordLength > 0)
                    {
                        byteBuffer.add((byte) '#');
                        byteBuffer.add(currWord);
                        byteBuffer.add((byte) (wordLength - 1));
                        wordLength = 0;
                    }
                    currWord = wordSymbol;
                    wordLength++;
                    currWordNr++;
                } else if(wordLength == 256)
                {
                    byteBuffer.add((byte) '#');
                    byteBuffer.add(currWord);
                    byteBuffer.add((byte) (wordLength - 1));
                    wordLength = 1;
                    currWordNr++;
                } else
                    wordLength++;
            }
        }
        byteBuffer.add((byte) '#');
        byteBuffer.add(currWord);
        byteBuffer.add((byte) (wordLength - 1));

        header.counter = currWordNr;

        byte[] finalBuffer = new byte[byteBuffer.size()];

        for (int i = 0; i < byteBuffer.size(); i++)
            finalBuffer[i] = byteBuffer.get(i);

        raf.write(header.toByteBuffer());

        raf.write(finalBuffer);

        raf.close();

    }

}
