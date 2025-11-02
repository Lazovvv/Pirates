package com.mycompany.pirates.UI;

import com.mycompany.pirates.Core.Theme;
import com.mycompany.pirates.Components.RoundedPanel;
import com.mycompany.pirates.Components.ThemeTogglePanel;
import com.mycompany.pirates.Components.TransparentImagePanel;
import com.mycompany.pirates.UI.MainPage.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class SignUpPage extends JFrame {
    private JTextField usernameField, emailField, birthdayField;
    private JPasswordField passwordField, confirmPasswordField;
    private JCheckBox termsCheckBox;
    private RoundedPanel signUpButton;

    public SignUpPage() {
        setTitle("Pirates Buffet - Sign Up");
        setSize(1920, 1036);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layers = new JLayeredPane();
        layers.setPreferredSize(new Dimension(1920, 1036));

        // Background
        JPanel bg = new JPanel();
        bg.setBackground(Theme.getBackground());
        bg.setBounds(0, 0, 1920, 1036);
        layers.add(bg, JLayeredPane.DEFAULT_LAYER);

        // Fonts
        String fontFolder = "src/fonts/";
        Font cooperTitle = loadFont(fontFolder + "CooperLtBT-Bold.ttf", 80f, Font.PLAIN);
        Font cooperSub = loadFont(fontFolder + "CooperLtBT-Bold.ttf", 40f, Font.PLAIN);
        Font cooperText = loadFont(fontFolder + "CooperLtBT-Regular.ttf", 18f, Font.PLAIN);
        Font robotoButton = loadFont(fontFolder + "Roboto-Bold.ttf", 22f, Font.PLAIN);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Theme.getSidePanel());
        leftPanel.setBounds(0, 0, 600, 1036);
        leftPanel.setOpaque(true);

        // Add padding around the content
        JPanel contentWrapper = new JPanel();
        contentWrapper.setLayout(new BoxLayout(contentWrapper, BoxLayout.Y_AXIS));
        contentWrapper.setBackground(Theme.getSidePanel());
        contentWrapper.setBorder(BorderFactory.createEmptyBorder(100, 120, 100, 120)); // top, left, bottom, right margins

        JLabel title = new JLabel("Pirates");
        title.setFont(cooperTitle);
        title.setForeground(Theme.getText());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subTitle = new JLabel("Sign Up");
        subTitle.setFont(cooperSub);
        subTitle.setForeground(Theme.getText());
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel switchLabel = new JLabel("Already have an account? ");
        switchLabel.setFont(cooperText);
        switchLabel.setForeground(Theme.getText());

        JLabel loginLink = new JLabel("Login Now");
        loginLink.setFont(cooperText);
        loginLink.setForeground(Theme.getAccent());
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginPage();
                dispose();
            }
        });

        JPanel switchPanel = new JPanel();
        switchPanel.setLayout(new BoxLayout(switchPanel, BoxLayout.X_AXIS));
        switchPanel.setBackground(Theme.getSidePanel());
        switchPanel.add(switchLabel);
        switchPanel.add(loginLink);
        switchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        switchPanel.setMaximumSize(new Dimension(350, 50));

        // Fields
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Theme.getSidePanel());
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension fieldSize = new Dimension(350, 45); // uniform size

        usernameField = createTextField(cooperText, fieldSize);
        emailField = createTextField(cooperText, fieldSize);
        passwordField = createPasswordField(cooperText, fieldSize);
        confirmPasswordField = createPasswordField(cooperText, fieldSize);
        birthdayField = createBirthdayField(cooperText, fieldSize);

        formPanel.add(createLabeledField("Username", usernameField, cooperText));
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(createLabeledField("Email", emailField, cooperText));
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(createLabeledField("Password", passwordField, cooperText));
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(createLabeledField("Confirm Password", confirmPasswordField, cooperText));
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(createLabeledField("Birthday (MM/DD/YYYY)", birthdayField, cooperText));

        // Terms Checkbox
        termsCheckBox = new JCheckBox("I agree to the Terms of Service & Privacy Policy");
        termsCheckBox.setFont(cooperText);
        termsCheckBox.setForeground(Theme.getText());
        termsCheckBox.setBackground(Theme.getSidePanel());
        termsCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Sign Up Button
        signUpButton = new RoundedPanel(70, Theme.getAccent().darker(),
                "Sign Up", robotoButton, Color.WHITE);
        signUpButton.setMaximumSize(new Dimension(220, 70));
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setEnabled(false);
        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addButtonListener(signUpButton);

        // Validation logic
        javax.swing.event.DocumentListener validator = new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { validateForm(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { validateForm(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { validateForm(); }
        };
        usernameField.getDocument().addDocumentListener(validator);
        emailField.getDocument().addDocumentListener(validator);
        passwordField.getDocument().addDocumentListener(validator);
        confirmPasswordField.getDocument().addDocumentListener(validator);
        birthdayField.getDocument().addDocumentListener(validator);
        termsCheckBox.addActionListener(e -> validateForm());

        // Add to wrapper
        contentWrapper.add(title);
        contentWrapper.add(subTitle);
        contentWrapper.add(Box.createVerticalStrut(15));
        contentWrapper.add(switchPanel);
        contentWrapper.add(Box.createVerticalStrut(30));
        contentWrapper.add(formPanel);
        contentWrapper.add(Box.createVerticalStrut(25));
        contentWrapper.add(termsCheckBox);
        contentWrapper.add(Box.createVerticalStrut(25));
        contentWrapper.add(signUpButton);

        leftPanel.add(contentWrapper);
        layers.add(leftPanel, JLayeredPane.DRAG_LAYER);

        // Right panel (image)
        JLayeredPane rightContainer = new JLayeredPane();
        rightContainer.setBounds(550, 0, 1920 - 550, 1036);
        TransparentImagePanel bgPanel = new TransparentImagePanel("src/images/Buffet.jpg", 0.22f);
        bgPanel.setBounds(0, 0, rightContainer.getWidth(), rightContainer.getHeight());
        rightContainer.add(bgPanel, new Integer(0));

        int imgSize = 1000;
        ImageIcon pirateImg = new ImageIcon(Theme.getLogoPath());
        Image scaled = pirateImg.getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setBounds(200, (rightContainer.getHeight() - imgSize) / 2, imgSize, imgSize);
        rightContainer.add(imageLabel, new Integer(1));

        layers.add(rightContainer, JLayeredPane.PALETTE_LAYER);

        // Theme toggle
        ThemeTogglePanel themeToggle = new ThemeTogglePanel();
        themeToggle.setBounds(1780, 20, 90, 40);
        layers.add(themeToggle, JLayeredPane.POPUP_LAYER);

        setContentPane(layers);
        setVisible(true);
    }

    // ---- HELPERS ----

    private JPanel createLabeledField(String labelText, JComponent field, Font font) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Theme.getSidePanel());

        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setForeground(Theme.getText());
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);

        return panel;
    }

    private JTextField createTextField(Font font, Dimension size) {
        JTextField field = new JTextField() {
            @Override
            public Insets getInsets() { return new Insets(5, 10, 5, 10); }
        };
        field.setMaximumSize(size);
        field.setPreferredSize(size);
        field.setFont(font);
        field.setBorder(BorderFactory.createLineBorder(Theme.getAccent(), 1, true));
        return field;
    }

    private JPasswordField createPasswordField(Font font, Dimension size) {
        JPasswordField field = new JPasswordField() {
            @Override
            public Insets getInsets() { return new Insets(5, 10, 5, 10); }
        };
        field.setMaximumSize(size);
        field.setPreferredSize(size);
        field.setFont(font);
        field.setBorder(BorderFactory.createLineBorder(Theme.getAccent(), 1, true));
        return field;
    }

    private JTextField createBirthdayField(Font font, Dimension size) {
        JTextField birthdayField = createTextField(font, size);
        birthdayField.setToolTipText("MM/DD/YYYY");

        birthdayField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '/') {
                    e.consume();
                    return;
                }

                String text = birthdayField.getText();

                if (Character.isDigit(c)) {
                    if (text.length() == 2 || text.length() == 5) birthdayField.setText(text + "/");
                    if (text.length() >= 10) {
                        e.consume();
                        return;
                    }
                }

                javax.swing.SwingUtilities.invokeLater(() -> {
                    String t = birthdayField.getText();
                    if (t.length() >= 2) {
                        try {
                            int month = Integer.parseInt(t.substring(0, 2));
                            if (month < 1 || month > 12) birthdayField.setText("");
                        } catch (Exception ignored) {}
                    }
                    if (t.length() >= 5) {
                        try {
                            int day = Integer.parseInt(t.substring(3, 5));
                            if (day < 1 || day > 31) birthdayField.setText(t.substring(0, 3));
                        } catch (Exception ignored) {}
                    }
                    if (t.length() == 10) {
                        try {
                            int year = Integer.parseInt(t.substring(6));
                            int currentYear = LocalDate.now().getYear();
                            if (year < 1900 || year > currentYear)
                                birthdayField.setText(t.substring(0, 6));
                        } catch (Exception ignored) {}
                    }
                });
            }
        });

        return birthdayField;
    }

    private void validateForm() {
        boolean filled = !usernameField.getText().isEmpty()
                && !emailField.getText().isEmpty()
                && passwordField.getPassword().length > 0
                && confirmPasswordField.getPassword().length > 0
                && !birthdayField.getText().isEmpty()
                && termsCheckBox.isSelected();

        signUpButton.setEnabled(filled);
        signUpButton.setCurrentColor(filled ? Theme.getAccent() : Theme.getAccent().darker());
        signUpButton.repaint();
    }

    private void addButtonListener(RoundedPanel panel) {
        Color normal = panel.getBaseColor();
        Color pressed = normal.darker();

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (panel.isEnabled()) {
                    panel.setCurrentColor(pressed);
                    panel.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!panel.isEnabled()) return;
                panel.setCurrentColor(normal);
                panel.repaint();

                JOptionPane.showMessageDialog(SignUpPage.this, "Account created successfully!");
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