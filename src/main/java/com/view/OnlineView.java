package com.view;

import com.constraints.ImageConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class OnlineView extends JFrame{

    private JPanel contentPane;
    private JButton btnLogout;
    private JButton btnChat;
    private JList list;

    /**
     * Create the frame.
     */
    public OnlineView(String username) {

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
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

        list = new JList(new DefaultListModel());
        list.setSelectionMode( ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPane.setViewportView(list);

        btnLogout = new JButton("");
        btnLogout.setBounds(350, 11, 50, 50);
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setIcon(new ImageIcon(ImageConstraints.img_logout));
        contentPane.add(btnLogout);

        btnChat = new JButton("Chat");
        btnChat.setBounds(30, 379, 89, 43);
        contentPane.add(btnChat);

    }

    public JList getList() {
        return list;
    }

    public void setList(DefaultListModel model) {
        list.setModel(model);
    }

    public void addChatListener(ActionListener listener){
        btnChat.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener){
        btnLogout.addActionListener(listener);
    }

    public void addCloseListener(WindowAdapter windowAdapter){
        this.addWindowListener(windowAdapter);
    }
}
