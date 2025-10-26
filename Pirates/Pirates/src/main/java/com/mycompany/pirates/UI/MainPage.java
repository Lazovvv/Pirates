package com.mycompany.pirates.UI;

import com.mycompany.pirates.Core.Theme;
import com.mycompany.pirates.Components.ThemeTogglePanel;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class MainPage extends JFrame {
    private JPanel contentArea; // main swappable area

    public MainPage() {
        setTitle("Pirates Buffet - Main Dashboard");
        setSize(1920, 1036);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Root layout
        JPanel root = new JPanel(new BorderLayout());

        // ---------- TOP BAR ----------
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setPreferredSize(new Dimension(1920, 80));
        topBar.setBackground(Theme.getBackground());

        // Left: Logo + Title
        JPanel leftTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        leftTop.setOpaque(false);
        ImageIcon rawLogo = new ImageIcon(Theme.getLogoPath());
        Image scaledLogo = rawLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaledLogo));
        
        
        JLabel title = new JLabel("Pirates: Buffet Restaurant");
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(Theme.getText());
        leftTop.add(logo);
        leftTop.add(title);

        // Right: Theme toggle + Username + Profile Icon
        JPanel rightTop = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        rightTop.setOpaque(false);

        ThemeTogglePanel togglePanel = new ThemeTogglePanel();
        togglePanel.setPreferredSize(new Dimension(90, 40));

        JLabel username = new JLabel("Username");
        username.setFont(new Font("Arial", Font.BOLD, 18));
        username.setForeground(Theme.getText());
        ImageIcon rawProfile = new ImageIcon("src/images/DefaultProfilePicture.jpg");
        Image scaledProfile = rawProfile.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel profile = new JLabel(new ImageIcon(scaledProfile));

        rightTop.add(togglePanel);
        rightTop.add(username);
        rightTop.add(profile);

        topBar.add(leftTop, BorderLayout.WEST);
        topBar.add(rightTop, BorderLayout.EAST);

        // ---------- SIDEBAR ----------

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(300, 1036));
        sidebar.setBackground(Theme.getAccent());
        sidebar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        sidebar.add(Box.createVerticalStrut(40)); // spacing

        sidebar.add(makeSidebarButton("Reservation Portal", "Book your buffet experience", "reservation"));
        sidebar.add(Box.createVerticalStrut(20));

        sidebar.add(makeSidebarButton("My Reservation", "View upcoming reservations", "myres"));
        sidebar.add(Box.createVerticalStrut(20));

        sidebar.add(makeSidebarButton("Rewards", "Check your earned rewards", "rewards"));

        // ---------- MAIN CONTENT ----------
        contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(Theme.getBackground().darker());
        setContent("Welcome");
        
        
        // ---------- ADD TO ROOT ----------
        root.add(topBar, BorderLayout.NORTH);
        root.add(sidebar, BorderLayout.WEST);
        root.add(contentArea, BorderLayout.CENTER);

        setContentPane(root);
        setVisible(true);
    }

    // Swaps content area
    private void setContent(String message) {
        contentArea.removeAll();
        
        if (message.equals("Welcome")) {
        // Show logo instead of text
            ImageIcon rawLogo = new ImageIcon(Theme.getLogoPath());
            Image scaledLogo = rawLogo.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            logoLabel.setVerticalAlignment(SwingConstants.CENTER);
            contentArea.add(logoLabel, BorderLayout.CENTER);
        } else {
            // Default behavior for other pages
            JLabel label = new JLabel(message, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 24));
            label.setForeground(Theme.getText());
            contentArea.add(label, BorderLayout.CENTER);
        }

    contentArea.revalidate();
    contentArea.repaint();
    }

    // Helper for sidebar buttons
    private JPanel makeSidebarButton(String title, String subtitle, String type) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Theme.getSidePanel());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panel.setMaximumSize(new Dimension(260, 80));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Theme.getText());

        JLabel subLabel = new JLabel(subtitle);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subLabel.setForeground(Theme.getText());

        panel.add(titleLabel);
        panel.add(subLabel);

        // Hover + click behavior
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Theme.getSidePanel());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(Theme.getSidePanel());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                switch (type) {
                    case "reservation":
                        setContent("Reservation Portal Page");
                        
                        break;
                    case "myres":
                        setContent("My Reservation Page");
                        break;
                    case "rewards":
                        setContent("Rewards Page");
                        break;
                    default:
                        setContent("Unknown page");
                }
            }
        });

        return panel;
    }
}