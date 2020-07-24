package com.controller;

import com.client.Client;
import com.view.ChatView;
import com.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChatController {
    LoginView loginView;
    ChatView chatView;
    private Client client;


    public ChatController(Client client, ChatView chatView) {

        this.chatView = chatView;
        this.client = client;
        chatView.addSendListener(new SendListener());
        chatView.addLogoutListener(new LogoutListener());
        chatView.addEmojiListener(new EmojiListener());
    }

    public void setTextArea(String msg, String name) {
        chatView.setTextArea(chatView.getTextArea() + '\n' + name + " to you:" +msg + '\n');
    }

    public String getTabOnline()
    {
        return chatView.getNameOnline().get(0);
    }
    public ArrayList<String> getListChat()
    {
        return chatView.getNameOnline();
    }

    public String getUsername()
    {
        return chatView.getUsername();
    }

    public void showChatView() {
        chatView.setVisible(true);
    }

    public void setTabOnline(DefaultListModel online) {
        chatView.setTable(online);
    }

    public String getTxtArea() {
        return chatView.getTextArea();
    }

    class EmojiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            chatView.showEmoji();
        }
    }

    class SendListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ArrayList<String> users = chatView.getNameOnline();
            System.out.println(users);
            if (!users.isEmpty()) {
                chatView.setTextArea(chatView.getTextArea() + '\n' + " + You to " + (users.size() <= 1 ? users.get(0) : "all") + ":" + chatView.getMsg() + '\n');
                StringBuilder msg = new StringBuilder();
                for (String user:users
                     ) {
                    msg.append(user+"#");
                }
                client.writeMessage("2#" + msg.toString()+ chatView.getMsg());
            }
            chatView.clearMsg();
        }
    }

    class LogoutListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //chatView.getClient().writeMessage("logout");
            loginView = new LoginView();
            LoginController controller = new LoginController(loginView);
            controller.showLoginView();
            chatView.setVisible(false);
        }
    }
}
