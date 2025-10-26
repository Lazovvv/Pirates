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

/**
 *
 * @author Lazov101
 */
public class LaunchPage extends JFrame {
    public LaunchPage() {
        setTitle("Pirates Buffet");
        setSize(1920, 1036);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Use a layered pane so the image can be centered on the WHOLE screen
        JLayeredPane layers = new JLayeredPane();
        layers.setPreferredSize(new Dimension(1920, 1036));

        // Main container
        Color bgColor = Theme.getBackground();
        JPanel bg = new JPanel();
        bg.setBackground(bgColor);
        bg.setBounds(0, 0, 1920, 1080);
        layers.add(bg, JLayeredPane.DEFAULT_LAYER);

        // Load fonts from your folder
        String fontFolder = "src/fonts/";
        Font cooperTitle = loadFont(fontFolder + "CooperLtBT-Bold.ttf", 100f, Font.PLAIN);
        Font cooperSub = loadFont(fontFolder + "CooperLtBT-Bold.ttf", 45f, Font.PLAIN);
        Font cooperText = loadFont(fontFolder + "CooperLtBT-Regular.ttf", 20f, Font.PLAIN);
        Font robotoButton = loadFont(fontFolder + "Roboto-Bold.ttf", 22f, Font.PLAIN);

        // LEFT PANEL (1/4 width)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(bgColor);
        leftPanel.setBounds(20, 0, 550, 1036);
        leftPanel.setOpaque(true);

        JLabel title = new JLabel("Pirates", SwingConstants.CENTER);
        title.setFont(cooperTitle);
        title.setForeground(Theme.getText());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subTitle = new JLabel("Ahoy, Mate!", SwingConstants.CENTER);
        subTitle.setFont(cooperSub);
        subTitle.setForeground(Theme.getAccent());
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // DESCRIPTION
        JTextPane description = new JTextPane();
        description.setText("Set sail for a legendary dining adventure at Pirates Buffet! "
                + "Reserve your spot at our bountiful table, where a treasure trove of flavors awaits. "
                + "Book now and join the crew for a swashbuckling feast!");
        description.setFont(cooperText);
        description.setForeground(Theme.getText());
        description.setBackground(bgColor);
        description.setEditable(false);
        description.setOpaque(false);
        description.setFocusable(false);
        description.setMaximumSize(new Dimension(420, 150));

        // Center paragraph alignment
        StyledDocument doc = description.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attrs, cooperText.getFamily());
        StyleConstants.setFontSize(attrs, cooperText.getSize());
        StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), attrs, false);

        // Buttons (RoundedPanels)
        RoundedPanel loginPanel = new RoundedPanel(70, Theme.getAccent(),
                "Log In", robotoButton, Color.WHITE);
        loginPanel.setMaximumSize(new Dimension(220, 70));
        loginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedPanel signupPanel = new RoundedPanel(70, Theme.getAccent(),
                "Sign Up", robotoButton, Color.WHITE);
        signupPanel.setMaximumSize(new Dimension(220, 70));
        signupPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add mouse listeners
        addButtonListener(loginPanel, "login");
        addButtonListener(signupPanel, "signup");

        // Add spacing and components to left panel
        leftPanel.add(Box.createVerticalStrut(210));
        leftPanel.add(title);
        leftPanel.add(subTitle);
        leftPanel.add(description);
        leftPanel.add(Box.createVerticalStrut(35));
        leftPanel.add(loginPanel);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(signupPanel);
        layers.add(leftPanel, JLayeredPane.DRAG_LAYER);

        // RIGHT SIDE
        JLayeredPane rightContainer = new JLayeredPane();
        rightContainer.setBounds(550, 0, 1920 - 550, 1036);

        // Transparent background image
        TransparentImagePanel bgPanel = new TransparentImagePanel(
                "src/images/Buffet.jpg", 0.22f
        );
        bgPanel.setBounds(420, 0, rightContainer.getWidth(), rightContainer.getHeight());
        rightContainer.add(bgPanel, new Integer(0));

        // CENTER IMAGE
        int imgSize = 1000;
        
        ImageIcon pirateImg = new ImageIcon(Theme.getLogoPath());
        Image scaled = pirateImg.getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setOpaque(false);
        imageLabel.setBounds(-90, (rightContainer.getHeight() - imgSize) / 2, imgSize, imgSize);
        rightContainer.add(imageLabel, new Integer(1));
        layers.add(rightContainer, JLayeredPane.PALETTE_LAYER);

        // THEME TOGGLE BUTTON
        ThemeTogglePanel themeToggle = new ThemeTogglePanel();
        themeToggle.setBounds(1780, 20, 90, 40); // position top-right
        layers.add(themeToggle, JLayeredPane.POPUP_LAYER);

        setContentPane(layers);
        setVisible(true);
    }

    // Utility: add mouse listeners to buttons
    private void addButtonListener(RoundedPanel panel, String type) {
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
                if (type.equals("login")) {
                    new LoginPage();
                    dispose();
                } else if (type.equals("signup")) {
                    new LaunchPage();
                    dispose();
                }
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

