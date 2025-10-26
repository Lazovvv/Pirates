/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author Lazov101
 */
public class RoundedPanel extends JPanel {
        private int cornerRadius;
        private Color baseColor;
        private Color currentColor;
        private String text;
        private Font font;
        private Color textColor;

        public RoundedPanel(int radius, Color bgColor, String text, Font font, Color textColor) {
            this.cornerRadius = radius;
            this.baseColor = bgColor;
            this.currentColor = bgColor;
            this.text = text;
            this.font = font;
            this.textColor = textColor;
            setOpaque(false);
            setPreferredSize(new Dimension(180, 48));
        }

        public Color getBaseColor() {
            return baseColor;
        }

        public void setCurrentColor(Color c) {
            this.currentColor = c;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(currentColor);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(),
                    cornerRadius, cornerRadius));

            g2.setFont(font);
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - (fm.getAscent() + fm.getDescent())) / 2 + fm.getAscent();
            g2.setColor(textColor);
            g2.drawString(text, x, y);

            g2.dispose();
        }
}
