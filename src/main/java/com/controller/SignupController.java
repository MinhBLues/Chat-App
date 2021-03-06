package com.controller;

import com.DAO.ReadWriteCSV;
import com.DAO.User;
import com.view.ChatView;
import com.view.LoginView;
import com.view.OnlineView;
import com.view.SignUpView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SignupController {
    SignUpView signupView;
    LoginView loginView;
    List<User> list;

    public SignupController(SignUpView signupView){
        this.signupView = signupView;
        signupView.addLoginListener(new LoginListener());
        signupView.addSignupListener(new SignupListener());
    }
    public void showSignup(){
        signupView.setVisible(true);
    }

    class LoginListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            loginView = new LoginView();
            LoginController controller = new LoginController(loginView);
            controller.showLoginView();
            signupView.setVisible(false);
        }
    }

    class SignupListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            if(signupView.checkEmpty()){
                JOptionPane.showMessageDialog(signupView.getContentPane(),"Please enter all fields");
                return;
            }
            if(!signupView.confirmPassword()){
                JOptionPane.showMessageDialog(signupView.getContentPane(),"Confirm password fail !");
                return;
            }
            User iuser = signupView.getUser();
            list = ReadWriteCSV.read();
            list.add(iuser);
            ReadWriteCSV.write(list);
            OnlineController controller = new OnlineController(iuser.getName());
            controller.show();
            signupView.setVisible(false);

        }
    }
}
