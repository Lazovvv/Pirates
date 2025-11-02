package com.mycompany.pirates.UI.MainPage;

import com.mycompany.pirates.Core.Theme;
import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    public ProfilePanel() {
        setBackground(Theme.getBackground());
        JLabel lbl = new JLabel("User Profile Details");
        lbl.setFont(new Font("Cooper Black", Font.PLAIN, 30));
        lbl.setForeground(Theme.getText());
        add(lbl);
    }
}