package AmazeingGui.GuiElements;

import javax.swing.*;
import java.awt.*;

class ControlPanelButton extends JButton {

    ControlPanelButton(String name, ImageIcon icon)
    {
        super(name);
        setFont(new Font("Arial", Font.PLAIN, 16));
        setIcon(icon);
    }
}
