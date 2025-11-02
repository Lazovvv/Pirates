package com.mycompany.pirates.Core;

import java.awt.*;
import javax.swing.*;

public class Theme {
    public static boolean darkMode = false; // default

    // === LIGHT COLORS ===
    private static final Color LIGHT_BG = new Color(0xED, 0xDC, 0xBD);
    private static final Color LIGHT_TEXT = new Color(60, 30, 10);
    private static final Color LIGHT_ACCENT = new Color(200, 100, 60);
    private static final Color LIGHT_SIDEPANEL = Color.WHITE;

    // === DARK COLORS ===
    private static final Color DARK_BG = new Color(34, 34, 34);
    private static final Color DARK_TEXT = new Color(220, 220, 220);
    private static final Color DARK_ACCENT = new Color(160, 60, 30);
    private static final Color DARK_SIDEPANEL = new Color(20, 20, 20);

    // === COLOR ACCESSORS ===
    public static Color getSidePanel() { return darkMode ? DARK_SIDEPANEL : LIGHT_SIDEPANEL; }
    public static Color getBackground() { return darkMode ? DARK_BG : LIGHT_BG; }
    public static Color getText() { return darkMode ? DARK_TEXT : LIGHT_TEXT; }
    public static Color getAccent() { return darkMode ? DARK_ACCENT : LIGHT_ACCENT; }
    public static Color getButtonBackground() { return darkMode ? new Color(60, 60, 60) : Color.WHITE; }
    public static Color getButtonText() { return getText(); }

    public static boolean isDarkMode() { return darkMode; }
    public static void setDarkMode(boolean mode) { darkMode = mode; }

    // === LOGO HANDLING ===
    public static String getLogoPath() {
        return darkMode ? "src/images/DarkPirateLogoNoBG.png"
                        : "src/images/PirateLogoNoBG.png";
    }

    // === THEME SWITCH ===
    public static void applyThemeSwitch(Component source) {
        darkMode = !darkMode; // Toggle

        // Ensure UI updates happen on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            Window win = SwingUtilities.getWindowAncestor(source);
            if (win != null) {
                updateComponentTreeUI(win);
                SwingUtilities.updateComponentTreeUI(win);
                win.revalidate();
                win.repaint();
            }
        });
    }

    // === RECURSIVE UI UPDATE ===
    private static void updateComponentTreeUI(Component component) {

        // === PANELS ===
        if (component instanceof JPanel panel) {

           String name = panel.getName();

            // Detect MainPage panels by name
            boolean isSidebar = "sidebar".equalsIgnoreCase(name);
            boolean isContent = "contentArea".equalsIgnoreCase(name);
            boolean isSidebarButton = "sidebarButton".equalsIgnoreCase(name);

            // Apply custom colors based on type
            if (isSidebar) {
                panel.setBackground(getAccent()); // main sidebar orange
            } else if (isContent) {
                panel.setBackground(getBackground().darker()); // content area
            } else if (isSidebarButton) {
                panel.setBackground(getSidePanel()); // side buttons default bg
            } else {
                // Generic panels
                panel.setBackground(getBackground());
            }

            panel.setForeground(getText());
        }

        // === LABELS ===
        if (component instanceof JLabel label) {
            label.setForeground(getText());

            // 🔹 Only update labels explicitly named "logo"
            if (label.getName() != null && label.getName().toLowerCase().contains("logo")) {
                int targetW = 0, targetH = 0;
                Icon currentIcon = label.getIcon();
                if (currentIcon instanceof ImageIcon imgIcon) {
                    targetW = imgIcon.getIconWidth();
                    targetH = imgIcon.getIconHeight();
                }

                if (targetW <= 0 || targetH <= 0) {
                    Dimension d = label.getSize();
                    if (d.width > 0 && d.height > 0) {
                        targetW = d.width;
                        targetH = d.height;
                    } else {
                        Dimension pref = label.getPreferredSize();
                        targetW = pref.width > 0 ? pref.width : 120;
                        targetH = pref.height > 0 ? pref.height : 120;
                    }
                }

                ImageIcon newIconOriginal = new ImageIcon(getLogoPath());
                Image newImg = newIconOriginal.getImage();

                int origW = newIconOriginal.getIconWidth();
                int origH = newIconOriginal.getIconHeight();
                if (origW > 0 && origH > 0) {
                    double scale = Math.min((double) targetW / origW, (double) targetH / origH);
                    int scaledW = Math.max(1, (int) (origW * scale));
                    int scaledH = Math.max(1, (int) (origH * scale));
                    Image scaled = newImg.getScaledInstance(scaledW, scaledH, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(scaled));
                } else {
                    label.setIcon(newIconOriginal);
                }
                label.revalidate();
                label.repaint();
            }
        }

        // === BUTTONS ===
        if (component instanceof JButton button) {
            button.setBackground(getButtonBackground());
            button.setForeground(getButtonText());
        }

        // === TEXT PANES ===
        if (component instanceof JTextPane pane) {
            pane.setBackground(getBackground());
            pane.setForeground(getText());
        }

        // === CUSTOM COMPONENTS ===
        if (component instanceof com.mycompany.pirates.Components.RoundedPanel rp) {
            rp.setCurrentColor(getAccent());
            rp.repaint();
        }

        if (component instanceof com.mycompany.pirates.Components.TransparentImagePanel tip) {
            tip.repaint();
        }

        // === RECURSION ===
        if (component instanceof Container container) {
            for (Component child : container.getComponents()) {
                updateComponentTreeUI(child);
            }
        }

        component.revalidate();
        component.repaint();
    }

    // === UTIL: Icon scaling helper ===
    private static void scaleLabelIcon(JLabel label, ImageIcon icon, int targetWidth) {
        int originalWidth = icon.getIconWidth();
        int originalHeight = icon.getIconHeight();
        int targetHeight = (int) ((double) targetWidth / originalWidth * originalHeight);
        Image scaled = icon.getImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaled));
        label.setPreferredSize(new Dimension(targetWidth, targetHeight));
    }
}