package com.controller;

import com.client.Client;
import com.view.ChatView;
import com.view.LoginView;
import com.view.OnlineView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ChatController {
    LoginView loginView;
    ChatView chatView;
    private Client client;
    private File file;

    public ChatController(Client client, ChatView chatView) {

        this.chatView = chatView;
        this.client = client;
        chatView.addSendListener(new SendListener());
        chatView.addLogoutListener(new LogoutListener());
        chatView.addEmojiListener(new EmojiListener());
        chatView.addSendFileListener(new SendFileLinter());
    }

    public void setTextArea(String msg, String name) {
        chatView.setTextArea(chatView.getTextArea() + '\n' + name + " to you:" + msg + '\n');
    }

    public String getTabOnline() {
        return chatView.getNameOnline().get(0);
    }

    public ArrayList<String> getListChat() {
        return chatView.getNameOnline();
    }

    public void showChatView() {
        chatView.setVisible(true);
    }

    public void setTabOnline(DefaultListModel online) {
        chatView.setTable(online);
    }

    class EmojiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            chatView.showEmoji();
        }
    }

    private FileInputStream fr = null;

    private byte bytefile[];

    class SendListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ArrayList<String> users = chatView.getNameOnline();
            if (!users.isEmpty() && !chatView.getMsg().trim().isEmpty()) {
                System.out.println(chatView.getMsg());
                chatView.setTextArea(chatView.getTextArea() + '\n' + " + You to " + (users.size() <= 1 ? users.get(0) : "all") + ":" + chatView.getMsg() + '\n');
                StringBuilder msg = new StringBuilder();
                for (String user : users
                ) {
                    msg.append(user + "#");
                }
                if (fr != null) {
                    if (chatView.getMsg().contains(file.getName())) {

                        try {
                            client.getOutput().writeUTF("3#" + msg.toString() + chatView.getMsg() + "#" + file.length());
                            client.getOutput().write(bytefile, 0, bytefile.length);

                        } catch (IOException ex) {
                            System.out.println("error 82");

                        }
                        fr = null;
                        file = null;
                        bytefile = null;
                    }
                } else {
                    try {
                        client.getOutput().writeUTF("2#" + msg.toString() + chatView.getMsg());
                    } catch (IOException ex) {
                        System.out.println("error 93");
                    }
                }
            }
            chatView.clearMsg();
        }
    }

    class LogoutListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            chatView.setVisible(false);
        }
    }

    class SendFileLinter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);

            int option = fileChooser.showOpenDialog(chatView.getContentPane());
            if (option == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                if (file.length() > 10485760) {
                    JOptionPane.showMessageDialog(chatView.getContentPane(), "File size > 10MB");
                    file = null;
                } else {
                    try {
                        fr = new FileInputStream(file);
                        bytefile = new byte[(int) file.length()];
                        fr.read(bytefile, 0, bytefile.length);
                        chatView.setTxtMessage(file.getName());
                        fr.close();

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
    }
}
