package com.view;


import com.client.Client;
import com.constraints.ImageConstraints;
import com.controller.ChatController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class OnlineView extends JFrame {

    private JPanel contentPane;
    private String[] columnNames = new String[]{"ONLINE"};
    private Object data = new Object[][]{};
    private JTable table;
    private JLabel lblUsername;
    private ArrayList<String> chatListSingle;
    private ArrayList<String> chatListGroup;

    /**
     * Create the frame.
     */
    public OnlineView(String username) {
        chatListGroup = new ArrayList<>();
        chatListSingle = new ArrayList<>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 451, 472);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblUsername = new JLabel("----");
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setBounds(50, 11, 184, 50);
        lblUsername.setText(username);
        lblUsername.setFont(new Font("Dialog", Font.ITALIC, 25));
        contentPane.add(lblUsername);

        JPanel panel = new JPanel();
        panel.setBounds(20, 74, 394, 304);
        contentPane.add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 374, 282);
        panel.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPane.setViewportView(table);

        JButton btnLogout = new JButton("");
        btnLogout.setBounds(350, 11, 50, 50);
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setIcon(new ImageIcon(ImageConstraints.img_logout));
        contentPane.add(btnLogout);

        JButton btnChat = new JButton("Chat");
        btnChat.setBounds(30, 379, 89, 43);
        btnChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int i = table.getSelectedRow();
                String username = i >= 0 ? table.getValueAt(i, 0).toString() : null;
                if (!username.isEmpty()) {
                    boxChat(lblUsername.getText());
                }
            }
        });
        contentPane.add(btnChat);
        showBoxRecevice(lblUsername.getText());

    }

    private ArrayList<ChatController> chatSingle = new ArrayList<>();
    private ArrayList<ChatController> chatGroup = new ArrayList<>();

    private Client client;

    public void showBoxRecevice(String username) {
        client = new Client(username);
        client.sendName();
        Runnable target;
        Thread readMsg = new Thread(new Runnable() {
            public void run() {
                try {
                    int i = 0;
                    Object[][] online = new Object[50][1];
                    while (true) {
                        String msg = client.getInput().readUTF();
                        String[] ex = msg.split("#");// pi#msg
                        if (ex.length > 1) {
                            if (ex.length == 3) {
                                if (!chatListSingle.contains(ex[0])) {
                                    ChatController chatController = new ChatController(client, new ChatView(lblUsername.getText()));
                                    DefaultListModel model = new DefaultListModel();
                                    model.addElement(ex[0]);
                                    chatController.setTabOnline(model);
                                    chatController.showChatView();
                                    chatController.setTextArea(ex[2], ex[0]);
                                    chatSingle.add(chatController);
                                    chatListSingle.add(ex[0]);
                                } else {
                                    for (int j = 0; j < chatSingle.size(); j++) {
                                        if (chatSingle.get(j).getTabOnline().equals(ex[0])) {
                                            chatSingle.get(j).setTextArea(ex[2], ex[0]);
                                        }
                                    }
                                }
                            } else // nguoigui#listuser#message
                            {
                                int flagGroup = -1;
                                for (int j = 0; j < chatListGroup.size(); j++) {
                                    boolean flag = true;
                                    for (int k = 0; k < ex.length - 1; k++) {
                                        if (chatListGroup.get(j).indexOf(ex[k]) < 0) {
                                            flag = false;
                                            break;
                                        }
                                    }
                                    if (flag) {
                                        flagGroup = j;
                                    }
                                }
                                if (flagGroup == -1) {
                                    ChatController chatController = new ChatController(client, new ChatView(lblUsername.getText()));
                                    DefaultListModel model = new DefaultListModel();
                                    for (int j = 0; j < ex.length - 1; j++) {
                                        if (lblUsername.getText().equals(ex[j])) continue;
                                        model.addElement(ex[j]);
                                    }
                                    chatController.setTabOnline(model);
                                    chatController.showChatView();
                                    chatController.setTextArea(ex[ex.length - 1], ex[0]);
                                    chatGroup.add(chatController);
                                    chatListGroup.add(msg.substring(0, msg.lastIndexOf("#")));
                                } else {
                                    for (int j = 0; j < chatGroup.size(); j++) {
                                        ArrayList<String> listUser = chatGroup.get(j).getListChat();
                                        int k = 0;
                                        for (k = 0; k < ex.length - 1; k++) {
                                            if (lblUsername.getText().equals(ex[k])) continue;
                                            if (!listUser.contains(ex[k])) break;
                                        }
                                        if (k == ex.length - 1) {
                                            chatGroup.get(j).setTextArea(ex[ex.length - 1], ex[0]);
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
                            setTable(online);
                        }
                    }

                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
            }
        });
        readMsg.start();

    }


    public void setTable(Object[][] online) {
        table.setModel(new DefaultTableModel(online, columnNames));
    }

    public void boxChat(String username) {

        int[] selectedrows = table.getSelectedRows();
        if (selectedrows.length == 1 && !chatListSingle.contains(table.getValueAt(0, 0).toString())) {
            ChatController chatController = new ChatController(client, new ChatView(username));
            DefaultListModel online = new DefaultListModel();
            online.addElement(table.getValueAt(selectedrows[0], 0).toString());
            chatController.setTabOnline(online);
            chatController.showChatView();
            chatListSingle.add(table.getValueAt(selectedrows[0], 0).toString());
            chatSingle.add(chatController);
        } else if (selectedrows.length > 0) {
            ChatController chatController = new ChatController(client, new ChatView(username));
            DefaultListModel online = new DefaultListModel();
            StringBuilder msg = new StringBuilder();
            for (int i = 0; i < selectedrows.length; i++) {
                online.addElement(table.getValueAt(selectedrows[i], 0).toString());
                msg.append(table.getValueAt(selectedrows[i], 0).toString());
                msg.append("#");
            }
            msg.append(lblUsername.getText());
            chatListGroup.add(msg.toString());
            chatController.setTabOnline(online);
            chatController.showChatView();
            chatGroup.add(chatController);
        }
    }
}
