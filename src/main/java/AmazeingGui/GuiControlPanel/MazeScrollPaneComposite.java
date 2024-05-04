package AmazeingGui.GuiControlPanel;

import javax.swing.*;
import java.awt.image.BufferedImage;

final class MazeScrollPaneComposite {
    private InternalMazeView internalMazeView;
    private final JScrollPane scrollPane;

    MazeScrollPaneComposite(BufferedImage mazeImage)
    {
        internalMazeView = new InternalMazeView(mazeImage);

        scrollPane = new JScrollPane(internalMazeView.getMazeViewPanel());
        scrollPane.setAutoscrolls(true);
    }

    JScrollPane getScrollPane() {
        return scrollPane;
    }

    void changeMazeImage(BufferedImage mazeImage)
    {
        internalMazeView = new InternalMazeView(mazeImage);
        scrollPane.setViewportView(internalMazeView.getMazeViewPanel());
    }
}
