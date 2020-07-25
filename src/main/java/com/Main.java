package com;

import com.controller.SignupController;
import com.view.SignUpView;



public class Main {
    public static void main(String[] args){
        SignUpView signupView = new SignUpView();
        SignupController controller = new SignupController(signupView);
        controller.showSignup();
    }
}
