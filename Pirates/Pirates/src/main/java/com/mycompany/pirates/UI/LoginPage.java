package com.mycompany.pirates.UI;

import com.mycompany.pirates.Components.RoundedPanel;
import com.mycompany.pirates.Core.Theme;
import com.mycompany.pirates.Components.ThemeTogglePanel;
import com.mycompany.pirates.Components.TransparentImagePanel;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("Pirates Buffet - Login");
        setSize(1920, 1036);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Use a layered pane
        JLayeredPane layers = new JLayeredPane();
        layers.setPreferredSize(new Dimension(1920, 1036));

        // Background
        Color bgColor = Theme.getBackground();
        JPanel bg = new JPanel();
        bg.setBackground(bgColor);
        bg.setBounds(0, 0, 1920, 1036);
        layers.add(bg, JLayeredPane.DEFAULT_LAYER);

        // Load fonts
        String fontFolder = "src/fonts/";
        Font cooperTitle = loadFont(fontFolder + "CooperLtBT-Bold.ttf", 80f, Font.PLAIN);
        Font cooperSub = loadFont(fontFolder + "CooperLtBT-Bold.ttf", 40f, Font.PLAIN);
        Font cooperText = loadFont(fontFolder + "CooperLtBT-Regular.ttf", 18f, Font.PLAIN);
        Font robotoButton = loadFont(fontFolder + "Roboto-Bold.ttf", 22f, Font.PLAIN);

        // LEFT PANEL
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Theme.getSidePanel());
        leftPanel.setBounds(0, 0, 600, 1036);
        leftPanel.setOpaque(true);

        // Title
        JLabel title = new JLabel("Pirates", SwingConstants.CENTER);
        title.setFont(cooperTitle);
        title.setForeground(Theme.getText());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle (Login)
        JLabel subTitle = new JLabel("Login", SwingConstants.CENTER);
        subTitle.setFont(cooperSub);
        subTitle.setForeground(Theme.getText());
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // "Don't have account?" label
        JLabel switchLabel = new JLabel("Don’t have an account? ");
        switchLabel.setFont(cooperText);
        switchLabel.setForeground(Theme.getText());

        JLabel signUpLink = new JLabel("Sign Up Now");
        signUpLink.setFont(cooperText);
        signUpLink.setForeground(Theme.getAccent());
        signUpLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LaunchPage();
                dispose();
            }
        });

        JPanel switchPanel = new JPanel();
        switchPanel.setLayout(new BoxLayout(switchPanel, BoxLayout.X_AXIS)); // compact inline
        switchPanel.setBackground(Theme.getSidePanel()); // or Theme.getBackground() if not fixed white
        switchPanel.add(switchLabel);
        switchPanel.add(signUpLink);
        switchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ✅ constrain size so it doesn’t push everything down
        switchPanel.setMaximumSize(new Dimension(300, 100));
        switchPanel.setPreferredSize(new Dimension(300, 100));
        switchPanel.setMinimumSize(new Dimension(300, 100));

        // Username field
        JTextField usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(400, 50));
        usernameField.setFont(cooperText);
        usernameField.setBorder(BorderFactory.createLineBorder(Theme.getAccent(), 1, true));

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(cooperText);
        usernameLabel.setForeground(Theme.getText());
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(400, 50));
        passwordField.setFont(cooperText);
        passwordField.setBorder(BorderFactory.createLineBorder(Theme.getAccent(), 1, true));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(cooperText);
        passwordLabel.setForeground(Theme.getText());
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login button
        RoundedPanel loginButton = new RoundedPanel(70, Theme.getAccent(),
                "Log In", robotoButton, Color.WHITE);
        loginButton.setMaximumSize(new Dimension(220, 70));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        addButtonListener(loginButton);

        // Add components to left panel
        leftPanel.add(Box.createVerticalStrut(150));
        leftPanel.add(title);
        leftPanel.add(subTitle);
        leftPanel.add(switchPanel);
        leftPanel.add(usernameLabel);
        leftPanel.add(usernameField);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordField);
        leftPanel.add(Box.createVerticalStrut(35));
        leftPanel.add(loginButton);

        layers.add(leftPanel, JLayeredPane.DRAG_LAYER);

        // RIGHT CONTAINER
        JLayeredPane rightContainer = new JLayeredPane();
        rightContainer.setBounds(550, 0, 1920 - 550, 1036);

        TransparentImagePanel bgPanel = new TransparentImagePanel(
                "src/images/Buffet.jpg", 0.22f);
        bgPanel.setBounds(0, 0, rightContainer.getWidth(), rightContainer.getHeight());
        rightContainer.add(bgPanel, new Integer(0));

        int imgSize = 1000;
        ImageIcon pirateImg = new ImageIcon(Theme.getLogoPath());
        Image scaled = pirateImg.getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setOpaque(false);
        imageLabel.setBounds(200, (rightContainer.getHeight() - imgSize) / 2,
                imgSize, imgSize);
        rightContainer.add(imageLabel, new Integer(1));

        layers.add(rightContainer, JLayeredPane.PALETTE_LAYER);
        
        // THEME TOGGLE BUTTON
        ThemeTogglePanel themeToggle = new ThemeTogglePanel();
        themeToggle.setBounds(1780, 20, 90, 40); // position top-right
        layers.add(themeToggle, JLayeredPane.POPUP_LAYER);

        setContentPane(layers);
        setVisible(true);
    }

    // Utility
    private void addButtonListener(RoundedPanel panel) {
        Color normal = panel.getBaseColor();
        Color pressed = normal.darker();
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                panel.setCurrentColor(pressed);
                panel.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                panel.setCurrentColor(normal);
                panel.repaint();
                new MainPage();
                dispose();
            }
        });
    }

    private Font loadFont(String path, float size, int style) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new java.io.File(path));
            return font.deriveFont(style, size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("Serif", style, (int) size);
        }
    }
    
}