package AmazeingGui.GuiControlPanel;

import javax.swing.*;
import java.awt.*;

final class StatusLabelPanel extends JPanel {
    private final JLabel panelLabel;
    StatusLabelPanel()
    {
        super();
        setLayout(new BorderLayout());

        panelLabel = new JLabel();
        panelLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        panelLabel.setHorizontalAlignment(0); //centered
        panelLabel.setOpaque(true);
        panelLabel.setBackground(new Color(78, 80, 82));

        add(panelLabel, BorderLayout.CENTER);

        setLabel("Gotowy do akcji! Wybierz labirynt.", false);
    }

    void setLabel(String text, boolean isError) {
        if(isError)
            panelLabel.setForeground(Color.red);
        else
            panelLabel.setForeground(Color.GREEN);

        panelLabel.setText(text);
    }
}
