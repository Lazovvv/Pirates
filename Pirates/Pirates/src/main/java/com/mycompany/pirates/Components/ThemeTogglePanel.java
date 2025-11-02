package com.mycompany.pirates.Components;

import com.mycompany.pirates.Core.Theme;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThemeTogglePanel extends JPanel {
    private String text;
    private boolean hover = false;

    public ThemeTogglePanel() {
        setOpaque(false);
        setPreferredSize(new Dimension(70, 40));
        updateText();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Theme.applyThemeSwitch(ThemeTogglePanel.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    private void updateText() {
        text = Theme.isDarkMode() ? "Dark" : "Light";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color panelColor;
        if (Theme.darkMode) {
            panelColor = hover ? Theme.getAccent().darker() : new Color(0, 0, 0);
        } else {
            panelColor = hover ? new Color(34, 34, 34) : new Color(220, 130, 80);
        }

        g2.setColor(panelColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.setColor(Color.WHITE);
        String label = Theme.darkMode ? "Dark" : "Light";
        FontMetrics fm = g2.getFontMetrics();
        int textX = 15;
        int textY = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(label, textX, textY);

        int iconX = getWidth() - 30;
        int iconY = 12;

        if (!Theme.darkMode) {
            g2.fillOval(iconX, iconY, 15, 15);
        } else {
            g2.fillOval(iconX, iconY, 15, 15);
            g2.setColor(new Color(0, 0, 0));
            g2.fillOval(iconX + 5, iconY, 15, 15);
        }

        g2.dispose();
    }
}