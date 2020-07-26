package com.view;

import com.constraints.ImageConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class OnlineView extends JFrame implements ActionListener{

    private JPanel contentPane;
    private String[] columnNames = new String[]{"ONLINE"};
    private Object data = new Object[][]{};
    private JTable table;
<<<<<<< Updated upstream:src/main/java/com/view/OnlineView.java
    private JLabel lblUsername;
    private ArrayList<String> chatListSingle;
    private ArrayList<String> chatListGroup;
    private JButton btnLogout;
=======
    private JButton btnChat;
>>>>>>> Stashed changes:ChatClient/src/main/java/com/view/OnlineView.java

    /**
     * Create the frame.
     */
<<<<<<< Updated upstream:src/main/java/com/view/OnlineView.java
    public OnlineView(String username){
        chatListGroup = new ArrayList<>();
        chatListSingle = new ArrayList<>();
=======
    public OnlineView(String username) {


>>>>>>> Stashed changes:ChatClient/src/main/java/com/view/OnlineView.java
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
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPane.setViewportView(table);

        btnLogout = new JButton("");
        btnLogout.setBounds(350, 11, 50, 50);
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setIcon(new ImageIcon(ImageConstraints.img_logout));
        contentPane.add(btnLogout);
        //btnLogout.addActionListener(this);


        btnChat = new JButton("Chat");
        btnChat.setBounds(30, 379, 89, 43);
        contentPane.add(btnChat);

    }

    public JTable getTable() {
        return table;
    }

    public void setTable(Object[][] online) {
        table.setModel(new DefaultTableModel(online, columnNames));
    }

    public void addChatListener(ActionListener listener){
        btnChat.addActionListener(listener);
    }
<<<<<<< Updated upstream:src/main/java/com/view/OnlineView.java
    public Client getClient(){
        return this.client;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void addLogoutListener(ActionListener listener){
        btnLogout.addActionListener(listener);
    }
=======

>>>>>>> Stashed changes:ChatClient/src/main/java/com/view/OnlineView.java
}