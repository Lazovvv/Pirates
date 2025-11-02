package com.mycompany.pirates.UI.MainPage;

import com.mycompany.pirates.Core.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SidebarButton extends JButton {

    private Color baseColor;
    private Color hoverColor;
    private Color pressedColor;

    public SidebarButton(String text) {
        super(text);
        setFont(new Font("SansSerif", Font.BOLD, 16));
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Theme.getText());
        setBackground(Theme.getButtonBackground());
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(200, 50));

        baseColor = Theme.getButtonBackground();
        hoverColor = Theme.getAccent();
        pressedColor = Theme.getAccent().darker();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(baseColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressedColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(hoverColor);
            }
        });
    }
}