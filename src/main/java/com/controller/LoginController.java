package com.controller;

import com.DAO.ReadWriteCSV;
import com.DAO.User;
import com.view.ChatView;
import com.view.LoginView;
import com.view.SignUpView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class LoginController {
    LoginView loginView;
    SignUpView signupView;
    ChatView chatView;
    List<User> list;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        loginView.addLoginListener(new LoginListener());
        loginView.addSignupListener(new SignupListener());
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (loginView.checkEmpty()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), "Please enter all fields");
                return;
            }
            list = ReadWriteCSV.read();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUsername().equals(loginView.getName())) {
                    if (list.get(i).getPassword().equals(loginView.getPassword())) {
                        chatView = new ChatView(loginView.getName());
                        loginView.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(loginView.getContentPane(), "Password wrong!");
                    }
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "User not exist !");
                }
            }
        }
    }

    class SignupListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            signupView = new SignUpView();
            SignupController controller = new SignupController(signupView);
            controller.showSignup();
            loginView.setVisible(false);
        }
    }
}
