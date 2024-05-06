package AmazeingGui.GuiControlPanel;

import AmazeingGui.MazeData;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public final class ControlPanelComposite {
    private final ButtonPanelComposite buttonPanelComposite;

    private final MazeScrollPaneComposite mazeViewPanelComposite;

    private final StatusLabelPanel statusLabelPanel;
    private MazeData mazeData;

    public ControlPanelComposite()
    {

        this.statusLabelPanel = new StatusLabelPanel();
        this.buttonPanelComposite = new ButtonPanelComposite();

        //temp for testing
        int[][] maze = new int[1000][1000];

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++)
            {
                if((i + j)%2 == 1)
                    maze[i][j] = MazeData.Wall;
                else
                    maze[i][j] = MazeData.Path;
            }
        }

        mazeData = new MazeData(1000, 1000, maze);

        mazeData.setEntry(0, 2);
        mazeData.setExit(0, 3);

        BufferedImage tempImage = MazeToImageConverter.convertMazeToImage(mazeData);

        this.mazeViewPanelComposite = new MazeScrollPaneComposite(tempImage);

    }

    public JPanel getButtonPanel() {
        return buttonPanelComposite.getControlJPanel();
    }

    public JPanel getStatusLabelJPanel() {
        return statusLabelPanel;
    }

    public JScrollPane getJScrollPane() {
        return mazeViewPanelComposite.getScrollPane();
    }

    public JLabel getFilenameLabel()
    {
        return buttonPanelComposite.getFilenameLabel();
    }

    public void setStatusLabel(String text, boolean IsError)
    {
        statusLabelPanel.setLabel(text, IsError);
    }

    public void setButtonActionListener(ButtonEnum button, ActionListener listener)
    {
        buttonPanelComposite.setButtonListener(button, listener);
    }

    public MazeData getMazeData() {
        return mazeData;
    }

    public void changeMazeData(MazeData mazeData)
    {
        this.mazeData = mazeData;
        BufferedImage tempImage = MazeToImageConverter.convertMazeToImage(mazeData);
        mazeViewPanelComposite.changeMazeImage(tempImage);
    }
}
