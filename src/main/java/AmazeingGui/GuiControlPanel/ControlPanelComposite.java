package AmazeingGui.GuiControlPanel;

import AmazeingGui.Coords;
import AmazeingGui.MazeData;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;


public final class ControlPanelComposite {
    private final ButtonPanelComposite buttonPanelComposite;

    private final MazeScrollPaneComposite mazeViewPanelComposite;

    private final StatusLabelPanel statusLabelPanel;

    private MazeData mazeData;

    public ControlPanelComposite()
    {

        this.statusLabelPanel = new StatusLabelPanel();
        this.buttonPanelComposite = new ButtonPanelComposite();
        setButtonState(ButtonEnum.solveButton, false);
        setButtonState(ButtonEnum.chooseExitButton, false);
        setButtonState(ButtonEnum.chooseEntranceButton, false);

        //temp for testing
        int[][] fill = new int[1000][1000];

        for(int[] row : fill)
            Arrays.fill(row, 0);

        mazeData = new MazeData(fill, new Coords(-1, -1), new Coords(-1, -1));

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

    public void setButtonState(ButtonEnum button, boolean state)
    {
        buttonPanelComposite.setButtonState(button, state);
    }

    public void changeMazeData(MazeData mazeData)
    {
        this.mazeData = mazeData;
        BufferedImage tempImage = MazeToImageConverter.convertMazeToImage(mazeData);
        mazeViewPanelComposite.changeMazeImage(tempImage);
    }
}
