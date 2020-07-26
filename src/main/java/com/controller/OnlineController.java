package com.controller;

import com.client.Client;
<<<<<<< Updated upstream:src/main/java/com/controller/OnlineController.java
import com.view.LoginView;
import com.view.OnlineView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
=======
import com.view.ChatView;
import com.view.OnlineView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
>>>>>>> Stashed changes:ChatClient/src/main/java/com/controller/OnlineController.java
import java.io.IOException;
import java.util.ArrayList;

public class OnlineController {
    OnlineView onlineView;
<<<<<<< Updated upstream:src/main/java/com/controller/OnlineController.java
    private ArrayList<String> chatList;
    LoginView loginView;
=======
    private ArrayList<String> chatListSingle = new ArrayList<>();
    private ArrayList<String> chatListGroup = new ArrayList<>();
    private ArrayList<ChatController> chatSingle = new ArrayList<>();
    private ArrayList<ChatController> chatGroup = new ArrayList<>();
    private Client client;
    String username;

>>>>>>> Stashed changes:ChatClient/src/main/java/com/controller/OnlineController.java
    public OnlineController(String username) {
        this.username = username;
        onlineView = new OnlineView(username);
<<<<<<< Updated upstream:src/main/java/com/controller/OnlineController.java
        chatList = new ArrayList<>();
        onlineView.addLogoutListener(new LogoutListener());
    }
    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            onlineView.getClient().writeMessage("4#" + onlineView.getName());
            onlineView.setVisible(false);
            loginView = new LoginView();
            LoginController controller = new LoginController(loginView);
            controller.showLoginView();
        }
=======
        onlineView.addChatListener(new ChatListener());
        showBoxRecevice();
>>>>>>> Stashed changes:ChatClient/src/main/java/com/controller/OnlineController.java
    }

    public void show() {
        onlineView.setVisible(true);
    }

    public void showBoxRecevice() {
        client = new Client(username);
        client.sendName();
        Thread readMsg = new Thread(new Runnable() {
            public void run() {
                try {
                    int i = 0;
                    Object[][] online = new Object[50][1];
                    while (true) {
                        String msg = client.getInput().readUTF();
                        if (msg.length() < 1) continue;
                        String[] ex = msg.split("#");// pi#msg
                        /**
                         TH1: Bắt đầu 1 boxchat
                         TH2: Đăng nhập/Đăng kí
                         */
                        if (ex.length > 1) {
                            /**
                             Chat single.
                             TH1: Gửi tin nhắn bình thường ex.length = 3. msg sẽ có cấu trúc  sender#receiver#message
                             TH2: Gửi tin nhắn kèm file ex.length = 5. msg sẽ có cấu trúc 3File3#sender#receiver#message#file.length
                             */
                            if (ex.length == 3 || (ex.length == 5 && ex[0].equals("3File3"))) {
                                String sender = ex[0];
                                String message = ex[2];
                                if (ex[0].equals("3File3")) {
                                    sender = ex[1];
                                    message = ex[3];
                                }
                                /**
                                 TH1: Sender và Receiver chưa chat single với nhau
                                 TH2: Sender và Receiver đang chat single với nhau
                                 */
                                if (!chatListSingle.contains(sender)) {
                                    ChatController chatController = new ChatController(client, new ChatView(username));
                                    DefaultListModel model = new DefaultListModel();
                                    chatController.setTabOnline(model);
                                    model.addElement(sender);
                                    chatController.setTextArea(message, sender);
                                    chatController.showChatView();
                                    if (ex[0].equals("3File3")) {
                                        chooseFile(chatController.chatView, ex[3], Integer.parseInt(ex[4]));
                                    }
                                    chatListSingle.add(sender);
                                    chatSingle.add(chatController);
                                } else {
                                    for (int j = 0; j < chatSingle.size(); j++) {
                                        if (chatSingle.get(j).getTabOnline().equals(sender)) {
                                            if (ex[0].equals("3File3")) {
                                                chooseFile(chatSingle.get(j).chatView, ex[3], Integer.parseInt(ex[4]));
                                            }
                                            try {
                                                chatSingle.get(j).setTextArea(message, sender);
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                    }
                                }
                            } else // nguoigui#listuser#message
                            /**
                             Chat group.
                             TH1: Gửi tin nhắn bình thường ex.length = 3. msg sẽ có cấu trúc  sender#receiverList#message
                             TH2: Gửi tin nhắn kèm file ex.length = 5. msg sẽ có cấu trúc 3File3#sender#receiverList#message#file.length
                             */ {
                                int flagGroup = -1;
                                String sender = ex[0];
                                String message = ex[ex.length - 1];
                                int index = 0, sizeEx = ex.length - 1;
                                if (ex[0].equals("3File3")) {
                                    sender = ex[1];
                                    message = ex[ex.length - 2];
                                    index = 1;
                                    sizeEx = ex.length - 2;
                                }
                                for (int j = 0; j < chatListGroup.size(); j++) {
                                    boolean flag = true;
                                    for (int k = index; k < sizeEx; k++) {
                                        if (chatListGroup.get(j).indexOf(ex[k]) < 0) {
                                            flag = false;
                                            break;
                                        }
                                    }
                                    if (flag) {
                                        flagGroup = j;
                                        break;
                                    }
                                }

                                if (flagGroup == -1) {
                                    ChatController chatController = new ChatController(client, new ChatView(username));
                                    DefaultListModel model = new DefaultListModel();

                                    for (int j = index; j < sizeEx; j++) {
                                        if (username.equals(ex[j])) continue;
                                        model.addElement(ex[j]);
                                    }
                                    chatController.setTabOnline(model);
                                    chatController.showChatView();
                                    chatController.setTextArea(message, sender);
                                    if (ex[0].equals("3File3")) {
                                        chooseFile(chatController.chatView, ex[ex.length - 2], Integer.parseInt(ex[ex.length - 1]));
                                    }
                                    chatGroup.add(chatController);
                                    chatListGroup.add(msg.substring(index, msg.lastIndexOf("#" + message)));
                                } else {
                                    for (int j = 0; j < chatGroup.size(); j++) {
                                        ArrayList<String> listUser = chatGroup.get(j).getListChat();
                                        int k = 0;

                                        for (k = index; k < sizeEx; k++) {
                                            if (username.equals(ex[k])) continue;
                                            if (!listUser.contains(ex[k])) break;
                                        }
                                        if (k == sizeEx) {
                                            if (ex[0].equals("3File3")) {
                                                chooseFile(chatGroup.get(j).chatView, ex[ex.length - 2], Integer.parseInt(ex[ex.length - 1]));
                                            }
                                            chatGroup.get(j).setTextArea(message, sender);
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            int j = 0;
                            for (j = 0; j < i; j++) {
                                if (msg.equals(online[j][0])) break;
                            }
                            if (j == i && !msg.equals(client.getUsername())) {
                                online[i][0] = msg;
                                i++;
                            }
                            onlineView.setTable(online);
                        }
                    }

                } catch (IOException e) {
                    System.out.println("error 168");
                }
            }
        });
        readMsg.start();
    }

    public void chooseFile(ChatView chatView, String name, int size) {

        int dialogResult = JOptionPane.showConfirmDialog(chatView.getContentPane(), username + " send to you a file. Do you want to receive the file?", "Receive", JOptionPane.YES_NO_OPTION);
        if (dialogResult == 0) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Open");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                byte bytefile[] = new byte[size];
                FileOutputStream fr = null;
                try {
                    fr = new FileOutputStream(new StringBuilder().append(chooser.getSelectedFile().toString()).append("\\" + name).toString());
                    System.out.println(chooser.getSelectedFile().toString());
                    client.getInput().read(bytefile, 0, size);
                    fr.write(bytefile, 0, size);
                    fr.close();

                } catch (IOException e) {
                    System.out.println("error 195");
                }
            } else {
                System.out.println("No Selection ");
            }
        } else {
            FileOutputStream fos = null;
            try {
                File file = new File("D:/" + name);

                if (!file.canRead()) {
                    file.setReadable(true);
                    file.setWritable(true);
                }
                fos = new FileOutputStream(file);
                byte bytefile[] = new byte[size];
                client.getInput().read(bytefile, 0, size);
                fos.write(bytefile, 0, size);
                fos.close();
                if (file.delete()) {
                    System.out.println("Delete file success");
                }
            } catch (IOException fnfe) {
                System.out.println("error fnfe 220");

            } finally {

                try {
                    if (fos != null)
                        fos.close();
                } catch (IOException ioe) {
                    System.out.println("error ioe 228");
                }
            }

        }
    }

    class ChatListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boxChat();
        }
    }

    public void boxChat() {

        int[] selectedrows = onlineView.getTable().getSelectedRows();
        if (selectedrows.length == 1 && !chatListSingle.contains(onlineView.getTable().getValueAt(0, 0).toString())) {
            ChatController chatController = new ChatController(client, new ChatView(username));
            DefaultListModel online = new DefaultListModel();
            online.addElement(onlineView.getTable().getValueAt(selectedrows[0], 0).toString());
            chatController.setTabOnline(online);
            chatController.showChatView();
            chatListSingle.add(onlineView.getTable().getValueAt(selectedrows[0], 0).toString());
            chatSingle.add(chatController);
        } else if (selectedrows.length > 0) {
            ChatController chatController = new ChatController(client, new ChatView(username));
            DefaultListModel online = new DefaultListModel();
            StringBuilder msg = new StringBuilder();
            for (int i = 0; i < selectedrows.length; i++) {
                online.addElement(onlineView.getTable().getValueAt(selectedrows[i], 0).toString());
                msg.append(onlineView.getTable().getValueAt(selectedrows[i], 0).toString());
                msg.append("#");
            }
            msg.append(username);
            chatListGroup.add(msg.toString());
            chatController.setTabOnline(online);
            chatController.showChatView();
            chatGroup.add(chatController);
        }
    }
}