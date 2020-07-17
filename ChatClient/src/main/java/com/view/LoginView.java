package com.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import static com.constraints.ImageConstraints.img_logo1;

public class LoginView extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblSignUp;


    /**
     * Create the frame.
     */
    public LoginView() {
        initcomponents();
    }

    public void initcomponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 610, 532);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 594, 493);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("LOG IN CHAT TOGETHER");
        lblTitle.setBounds(313, 81, 269, 47);
        lblTitle.setFont(new Font("Verdana", Font.PLAIN, 20));
        lblTitle.setForeground(new Color(120, 90, 40));
        panel.add(lblTitle);

        JLabel lblLogo = new JLabel("");
        lblLogo.setBounds(10, 11, 286, 462);
        lblLogo.setIcon(new ImageIcon(img_logo1));
        panel.add(lblLogo);

        JLabel lblUsername = new JLabel("Enter your username:");
        lblUsername.setBounds(334, 135, 169, 32);
        lblUsername.setForeground(new Color(120, 90, 40));
        lblUsername.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(332, 178, 250, 30);
        panel.add(txtUsername);
        txtUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Enter your password:");
        lblPassword.setBounds(334, 221, 169, 32);
        lblPassword.setForeground(new Color(120, 90, 40));
        lblPassword.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(334, 264, 250, 30);
        panel.add(txtPassword);

        lblSignUp = new JLabel("You haven't account?  Go to sign up");
        lblSignUp.setBounds(358, 305, 212, 32);
        lblSignUp.setForeground(Color.BLUE);
        lblSignUp.setFont(new Font("Verdana", Font.PLAIN, 10));
        panel.add(lblSignUp);

        btnLogin = new JButton("Log in");
        btnLogin.setBounds(403, 348, 100, 47);
        btnLogin.setForeground(new Color(120, 90, 40));
        btnLogin.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel.add(btnLogin);

    }

    public String getName() {
        return txtUsername.getText().trim();
    }

    public String getPassword() {
        return String.copyValueOf(txtPassword.getPassword());
    }

    public void actionPerformed(ActionEvent e) {
    }

    public boolean checkEmpty() {
        if (txtUsername.getText().equals("") || String.copyValueOf(txtPassword.getPassword()).equals("")) {
            return true;
        }
        return false;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public void addSignupListener(MouseListener listener) {
        lblSignUp.addMouseListener(listener);
    }

}
