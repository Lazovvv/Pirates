package com.mycompany.pirates.Core;


import java.awt.*;
import javax.swing.*;

public class Theme {
    public static boolean darkMode = false; // default

    // Light colors
    private static final Color LIGHT_BG = new Color(0xED, 0xDC, 0xBD);
    private static final Color LIGHT_TEXT = new Color(60, 30, 10);
    private static final Color LIGHT_ACCENT = new Color(200, 100, 60);
    private static final Color LIGHT_SIDEPANEL = Color.WHITE;

    // Dark colors
    private static final Color DARK_BG = new Color(34, 34, 34);
    private static final Color DARK_TEXT = new Color(220, 220, 220);
    private static final Color DARK_ACCENT = new Color(160, 60, 30);
    private static final Color DARK_SIDEPANEL = new Color(20, 20, 20);

    public static Color getSidePanel() {
        return darkMode ? DARK_SIDEPANEL : LIGHT_SIDEPANEL;
    }

    public static Color getBackground() {
        return darkMode ? DARK_BG : LIGHT_BG;
    }

    public static Color getText() {
        return darkMode ? DARK_TEXT : LIGHT_TEXT;
    }

    public static Color getAccent() {
        return darkMode ? DARK_ACCENT : LIGHT_ACCENT;
    }

    public static Color getButtonBackground() {
        return darkMode ? new Color(60, 60, 60) : Color.WHITE;
    }

    public static Color getButtonText() {
        return getText(); // reuse main text color
    }

    public static boolean isDarkMode() {
        return darkMode;
    }

    public static void setDarkMode(boolean mode) {
        darkMode = mode;
    }

    public static String getLogoPath() {
        return darkMode ? "src/images/DarkPirateLogoNoBG.png"
                        : "src/images/PirateLogoNoBG.png";
    }

    public static void applyThemeSwitch(Component source) {
        darkMode = !darkMode; // Toggle instantly

        Window win = SwingUtilities.getWindowAncestor(source);
        if (win != null) {
            updateComponentTreeUI(win);
        }
}

    private static void updateComponentTreeUI(Component component) {
        if (component instanceof JPanel panel) {
            panel.setBackground(getBackground());
            panel.setForeground(getText());
        }

        if (component instanceof JLabel label) {
            label.setForeground(getText());
            if (label.getIcon() != null) {
                // Update logo image if necessary
                ImageIcon icon = new ImageIcon(getLogoPath());
                label.setIcon(icon);
            }
        }

        if (component instanceof JButton button) {
            button.setBackground(getButtonBackground());
            button.setForeground(getButtonText());
        }

        if (component instanceof JTextPane pane) {
            pane.setBackground(getBackground());
            pane.setForeground(getText());
        }

        // ✅ Custom components
        if (component instanceof com.mycompany.pirates.Components.RoundedPanel rp) {
            rp.setCurrentColor(getAccent());
            rp.repaint();
        }

        if (component instanceof com.mycompany.pirates.Components.TransparentImagePanel tip) {
            tip.repaint();
        }

        if (component instanceof Container container) {
            for (Component child : container.getComponents()) {
                updateComponentTreeUI(child);
            }
        }

        component.repaint();
        component.revalidate();
    }
}
