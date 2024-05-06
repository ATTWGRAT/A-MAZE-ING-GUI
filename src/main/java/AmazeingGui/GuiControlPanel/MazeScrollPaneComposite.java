package AmazeingGui.GuiControlPanel;

import javax.swing.*;
import java.awt.image.BufferedImage;

final class MazeScrollPaneComposite {
    private MazeViewPanel internalMazeView;
    private final JScrollPane scrollPane;

    MazeScrollPaneComposite(BufferedImage mazeImage)
    {
        internalMazeView = new MazeViewPanel(mazeImage);

        scrollPane = new JScrollPane(internalMazeView);
        scrollPane.setAutoscrolls(true);
    }

    JScrollPane getScrollPane() {
        return scrollPane;
    }

    void changeMazeImage(BufferedImage mazeImage)
    {
        internalMazeView = new MazeViewPanel(mazeImage);
        scrollPane.setViewportView(internalMazeView);
    }
}
