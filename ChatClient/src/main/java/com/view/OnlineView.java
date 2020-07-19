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

public class OnlineView extends JFrame {

    private JPanel contentPane;
    private String[] columnNames = new String[]{"ONLINE"};
    private Object data = new Object[][]{};
    private JTable table;

    /**
     * Create the frame.
     */
    public OnlineView(String username) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 451, 472);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("----");
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
                    boxChat(username);
                }
            }
        });
        contentPane.add(btnChat);
        showBoxRecevice(username);

    }
    private Client client;

    public void showBoxRecevice(String username)
    {
        client = new Client(username);
        ChatView chatView = new ChatView(username);
        ChatController chatController = new ChatController(chatView);
        Runnable target;
        Thread readMsg = new Thread(new Runnable() {
            public void run() {
                try {
                    int i = 0;
                    Object[][] online = new Object[50][1];
                    while (true) {
                        String msg = client.getInput().readUTF();
                        String[] ex = msg.split(":");
                        if (ex.length > 1) {
                            chatController.showChatView();
                            chatView.setTextArea(chatView.getTextArea() + '\n' + msg + '\n');
                        } else {
                            int j = 0;
                            for (j = 0; j < i; j++) {
                                if (msg.equals(online[j][0])) break;
                            }
                            if (j == i && !msg.equals(client.getUsername())) {
                                online[i++][0] = msg;
                            }
                            chatView.setTable(online);
                        }
                    }
                } catch (IOException e) {
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
        ChatView chatView = new ChatView(username);
        ChatController chatController = new ChatController(chatView);
        chatController.showChatView();
    }
}
