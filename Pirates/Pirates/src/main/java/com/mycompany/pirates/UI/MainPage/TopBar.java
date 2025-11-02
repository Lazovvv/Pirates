package com.mycompany.pirates.UI.MainPage;

import com.mycompany.pirates.Components.ThemeTogglePanel;
import com.mycompany.pirates.Core.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TopBar extends JPanel {

    public TopBar(String username) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1920, 80));
        setBackground(Theme.getSidePanel());

        // Left Section (Logo + Title)
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        leftPanel.setOpaque(false);

        JLabel logo = new JLabel(new ImageIcon(Theme.getLogoPath()));
        logo.setName("mainLogo");
        logo.setPreferredSize(new Dimension(60, 60));

        JLabel title = new JLabel("Pirates Reservation Portal");
        title.setFont(new Font("Serif", Font.BOLD, 26));
        title.setForeground(Theme.getText());

        leftPanel.add(logo);
        leftPanel.add(title);

        // Right Section (Theme toggle + Username + Profile icon)
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        rightPanel.setOpaque(false);

        ThemeTogglePanel themeToggle = new ThemeTogglePanel();

        JLabel userLabel = new JLabel(username);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        userLabel.setForeground(Theme.getText());

        JLabel profileIcon = new JLabel();
        ImageIcon icon = new ImageIcon("src/images/DefaultProfilePicture.png");
        Image scaled = icon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        profileIcon.setIcon(new ImageIcon(scaled));
        profileIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        profileIcon.setToolTipText("View Profile");

        profileIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Profile clicked!\n(You can open the profile page here.)",
                        "Profile",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        rightPanel.add(themeToggle);
        rightPanel.add(userLabel);
        rightPanel.add(profileIcon);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }
}