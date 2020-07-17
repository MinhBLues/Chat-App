package com;

import com.controller.SignupController;
import com.view.SignUpView;

public class Main {
    public static void main(String[] args){
//        LoginView loginView = new LoginView();
//        LoginController controller = new LoginController(loginView);
//        controller.showLoginView();
        SignUpView signupView = new SignUpView();
        SignupController controller = new SignupController(signupView);
        controller.showSignup();
        //  ChatController chatController = new ChatController("minh");
    }
}
