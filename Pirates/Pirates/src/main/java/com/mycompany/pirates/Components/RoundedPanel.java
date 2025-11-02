package com.mycompany.pirates.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 * Flexible rounded panel that can be used as:
 *  - new RoundedPanel(radius)                   // simple colored rounded panel
 *  - new RoundedPanel(radius, bg, text, font, color) // labeled rounded panel
 */
public class RoundedPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private int cornerRadius;
    private Color baseColor;
    private Color currentColor;
    private String text;
    private Font font;
    private Color textColor;

    // ---------- Simple constructor (used by SignUpPage formPanel = new RoundedPanel(40);) ----------
    public RoundedPanel(int radius) {
        this.cornerRadius = radius;
        this.baseColor = new Color(255, 255, 255); // default white (will be visually overridden by Theme if you set it)
        this.currentColor = this.baseColor;
        this.text = null;
        this.font = new Font("Arial", Font.PLAIN, 14);
        this.textColor = Color.BLACK;
        setOpaque(false);
    }

    // ---------- Full constructor (keeps backward compatibility) ----------
    public RoundedPanel(int radius, Color bgColor, String text, Font font, Color textColor) {
        this.cornerRadius = radius;
        this.baseColor = bgColor != null ? bgColor : new Color(255, 255, 255);
        this.currentColor = this.baseColor;
        this.text = text;
        this.font = font != null ? font : new Font("Arial", Font.PLAIN, 14);
        this.textColor = textColor != null ? textColor : Color.BLACK;
        setOpaque(false);
        setPreferredSize(new Dimension(180, 48));
    }

    // ---------- Getters / Setters ----------
    public Color getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
        this.currentColor = baseColor;
        repaint();
    }

    public void setCurrentColor(Color c) {
        this.currentColor = c;
        repaint();
    }

    public void setText(String text) {
        this.text = text;
        repaint();
    }

    public void setTextFont(Font font) {
        this.font = font;
        repaint();
    }

    public void setTextColor(Color color) {
        this.textColor = color;
        repaint();
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    // ---------- Painting ----------
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Ensure we have a color to draw
        Color fill = currentColor != null ? currentColor : (baseColor != null ? baseColor : getBackground());
        g2.setColor(fill);

        // Draw rounded rectangle
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

        // Draw text if present
        if (text != null && !text.isEmpty()) {
            g2.setFont(font);
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - (fm.getAscent() + fm.getDescent())) / 2 + fm.getAscent();
            g2.setColor(textColor);
            g2.drawString(text, x, y);
        }

        g2.dispose();
    }
}