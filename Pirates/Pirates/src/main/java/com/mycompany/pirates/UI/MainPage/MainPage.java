package com.mycompany.pirates.UI.MainPage;

import com.mycompany.pirates.Core.Theme;
import com.mycompany.pirates.Components.ThemeTogglePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends JFrame {
    private JPanel contentArea;
    private JPanel sidebar;
    private JPanel topBar;
    private JLabel title;
    private JLabel logo;
    private JLabel username;
    private JLabel profile;
    private ThemeTogglePanel togglePanel;
    private List<JPanel> sidebarButtons = new ArrayList<>();

    public MainPage() {
        setTitle("Pirates Buffet - Main Dashboard");
        setSize(1920, 1036);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Root layered pane
        JLayeredPane layers = new JLayeredPane();
        layers.setPreferredSize(new Dimension(1920, 1036));

        // Background
        JPanel bg = new JPanel();
        bg.setBackground(Theme.getBackground());
        bg.setBounds(0, 0, 1920, 1036);
        layers.add(bg, JLayeredPane.DEFAULT_LAYER);

        // Root layout container
        JPanel root = new JPanel(new BorderLayout());
        root.setOpaque(false);
        root.setBounds(0, 0, 1920, 1036);

        // ---------- TOP BAR ----------
        topBar = new JPanel(new BorderLayout());
        topBar.setPreferredSize(new Dimension(1920, 80));
        topBar.setBackground(Theme.getBackground());

        // Left side: logo + title
        JPanel leftTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        leftTop.setOpaque(false);

        ImageIcon rawLogo = new ImageIcon(Theme.getLogoPath());
        Image scaledLogo = rawLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(scaledLogo));
        logo.setName("logo");

        title = new JLabel("Pirates: Buffet Restaurant");
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(Theme.getText());

        leftTop.add(logo);
        leftTop.add(title);

        // Right side: theme toggle + username + profile
        JPanel rightTop = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        rightTop.setOpaque(false);

        togglePanel = new ThemeTogglePanel();
        togglePanel.setPreferredSize(new Dimension(90, 40));

        username = new JLabel("Username");
        username.setFont(new Font("Arial", Font.BOLD, 18));
        username.setForeground(Theme.getText());

        ImageIcon rawProfile = new ImageIcon("src/images/DefaultProfilePicture.jpg");
        Image scaledProfile = rawProfile.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        profile = new JLabel(new ImageIcon(scaledProfile));
        profile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setContent("Profile Page");
            }
        });

        rightTop.add(togglePanel);
        rightTop.add(username);
        rightTop.add(profile);

        topBar.add(leftTop, BorderLayout.WEST);
        topBar.add(rightTop, BorderLayout.EAST);

        // ---------- SIDEBAR ----------
        sidebar = new JPanel();
        sidebar.setName("sidebar");
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(300, 1036));
        sidebar.setBackground(Theme.getAccent());
        sidebar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        sidebar.add(Box.createVerticalStrut(40));
        sidebar.add(makeSidebarButton("Reservation Portal", "Book your buffet experience", "reservation"));
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(makeSidebarButton("My Reservation", "View upcoming reservations", "myres"));
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(makeSidebarButton("Rewards", "Check your earned rewards", "rewards"));

        // ---------- MAIN CONTENT ----------
        contentArea = new JPanel(new BorderLayout());
        contentArea.setName("contentArea");
        contentArea.setBackground(Theme.getBackground().darker());
        setContent("Welcome");

        // ---------- ROOT COMPOSITION ----------
        root.add(topBar, BorderLayout.NORTH);
        root.add(sidebar, BorderLayout.WEST);
        root.add(contentArea, BorderLayout.CENTER);
        layers.add(root, JLayeredPane.PALETTE_LAYER);

        setContentPane(layers);
        setVisible(true);

        // ✅ Call refresh once to ensure correct colors
        refreshTheme();

        // ✅ Reapply theme colors when toggler is used
        togglePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SwingUtilities.invokeLater(() -> refreshTheme());
            }
        });
    }

    // -------- THEME REFRESHER --------
    private void refreshTheme() {
        // Top bar
        topBar.setBackground(Theme.getBackground());
        title.setForeground(Theme.getText());
        username.setForeground(Theme.getText());

        // Update logo (consistent size)
        ImageIcon newLogo = new ImageIcon(Theme.getLogoPath());
        Image scaled = newLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(scaled));

        // Sidebar
        sidebar.setBackground(Theme.getAccent());

        // Sidebar buttons
        for (JPanel btn : sidebarButtons) {
            btn.setBackground(Theme.getSidePanel());
            for (Component c : btn.getComponents()) {
                if (c instanceof JLabel lab) {
                    lab.setForeground(Theme.getText());
                }
            }
        }

        // Content area background
        contentArea.setBackground(Theme.getBackground().darker());

        // ✅ NEW: recolor text or logo inside content area
        for (Component comp : contentArea.getComponents()) {
            if (comp instanceof JLabel label) {
                // If it’s a text label
                if (label.getName() == null || !label.getName().equals("logo")) {
                    label.setForeground(Theme.getText());
                } else {
                    // If it's the logo, reload it with new theme
                    ImageIcon newLogoCenter = new ImageIcon(Theme.getLogoPath());
                    Image scaledLogoCenter = newLogoCenter.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(scaledLogoCenter));
                }
            }
        }

        // Repaint everything
        contentArea.revalidate();
        contentArea.repaint();
        revalidate();
        repaint();
    }

    // -------- CONTENT SWITCHER --------
    private void setContent(String message) {
        contentArea.removeAll();

        if (message.equals("Welcome")) {
            ImageIcon rawLogo = new ImageIcon(Theme.getLogoPath());
            Image scaledLogo = rawLogo.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            logoLabel.setVerticalAlignment(SwingConstants.CENTER);
            logoLabel.setName("logo");
            contentArea.add(logoLabel, BorderLayout.CENTER);
        } else {
            JLabel label = new JLabel(message, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 24));
            label.setForeground(Theme.getText());
            contentArea.add(label, BorderLayout.CENTER);
        }

        contentArea.revalidate();
        contentArea.repaint();
    }

    // -------- SIDEBAR BUTTON FACTORY --------
    private JPanel makeSidebarButton(String title, String subtitle, String type) {
        JPanel panel = new JPanel();
        panel.setName("sidebarButton");
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
        sidebarButtons.add(panel);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Theme.getAccent().darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(Theme.getSidePanel());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                switch (type) {
                    case "reservation" -> setContent("Reservation Portal Page");
                    case "myres" -> setContent("My Reservation Page");
                    case "rewards" -> setContent("Rewards Page");
                    default -> setContent("Unknown Page");
                }
            }
        });

        return panel;
    }
}