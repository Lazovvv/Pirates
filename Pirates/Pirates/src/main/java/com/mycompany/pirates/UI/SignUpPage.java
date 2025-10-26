/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirates.UI;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Lazov101
 */

public class SignUpPage extends JFrame {
    public SignUpPage() {
        setTitle("Sign Up Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("This is the Sign Up Page", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 24));

        add(label, BorderLayout.CENTER);
        setVisible(true);
    }
}