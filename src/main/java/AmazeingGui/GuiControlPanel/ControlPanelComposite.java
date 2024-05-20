package AmazeingGui.GuiControlPanel;

import AmazeingGui.Coords;
import AmazeingGui.MazeData;
import AmazeingGui.MazeToImageConverter;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;


public final class ControlPanelComposite {
    private final ButtonPanelComposite buttonPanelComposite;

    private final MazeScrollPaneComposite mazeViewPanelComposite;

    private final StatusLabelPanel statusLabelPanel;

    private MazeData mazeData;

    private boolean isChoosingExit;
    private boolean isChoosingEntry;

    public ControlPanelComposite()
    {
        isChoosingEntry = false;
        isChoosingExit = false;

        this.statusLabelPanel = new StatusLabelPanel();
        this.buttonPanelComposite = new ButtonPanelComposite();

        setButtonState(ButtonEnum.solveButton, false);
        setButtonState(ButtonEnum.chooseExitButton, false);
        setButtonState(ButtonEnum.chooseEntranceButton, false);
        setButtonState(ButtonEnum.writeFileButton, false);

        //temp for testing
        int[][] fill = new int[1000][1000];

        for(int[] row : fill)
            Arrays.fill(row, 0);

        mazeData = new MazeData(fill, null, null);

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

    public boolean isChoosingExit() {
        return isChoosingExit;
    }

    public void changeChoosingExit() {
        isChoosingExit = !isChoosingExit;
    }

    public boolean isChoosingEntry() {
        return isChoosingEntry;
    }

    public void changeChoosingEntry() {
        isChoosingEntry = !isChoosingEntry;
    }

    public void setNewExit(Coords oldCoords, Coords newCoords)
    {
        BufferedImage image = mazeViewPanelComposite.getMazeImage();

        MazeToImageConverter.setExitToImage(image, oldCoords, newCoords, mazeData);

        mazeViewPanelComposite.revalidateView();
    }

    public void setNewEntry(Coords oldCoords, Coords newCoords)
    {
        BufferedImage image = mazeViewPanelComposite.getMazeImage();

        MazeToImageConverter.setEntryToImage(image, oldCoords, newCoords, mazeData);

        mazeViewPanelComposite.revalidateView();
    }

    public void addMouseListener(MouseListener listener)
    {
        mazeViewPanelComposite.addMouseListener(listener);
    }

    public MazeData getMazeData() {
        return mazeData;
    }
}
