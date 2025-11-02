package com.mycompany.pirates.UI.MainPage;

import com.mycompany.pirates.Core.Theme;
import javax.swing.*;
import java.awt.*;

public class ReservationPanel extends JPanel {
    public ReservationPanel() {
        setBackground(Theme.getBackground());
        JLabel lbl = new JLabel("Reservation Portal");
        lbl.setFont(new Font("Cooper Black", Font.PLAIN, 30));
        lbl.setForeground(Theme.getText());
        add(lbl);
    }
}