package com.view;

import com.ImageConstraints;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ChatView extends JFrame {

    private JPanel contentPane;
    private String username;
    private JTextField txtMessage;
    private JTable table;
    private JTextArea txtAreaChat;
    private JButton btnFile;
    private JButton btnSend;
    private JButton btnLogout;

    private String[] columnNames = new String[]{"ONLINE"};
    private Object data = new Object[][]{};

    /**
     * Create the frame.
     */
    public ChatView(String username) {

        this.username = username;

        initcomponents();
    }

    public void initcomponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 734, 547);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        /**
         * panel1
         */
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(255, 250, 205));
        panel1.setBounds(10, 11, 208, 486);
        contentPane.add(panel1);
        panel1.setLayout(null);

        JLabel lblLogo = new JLabel("");
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(10, 11, 188, 200);
        lblLogo.setIcon(new ImageIcon(ImageConstraints.img_logo));
        panel1.add(lblLogo);

        JLabel lblUsername = new JLabel("-----"+this.username+"-----");
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(new Color(120, 90, 40));
		lblUsername.setFont(new Font("Verdana", Font.PLAIN, 15));
        lblUsername.setBounds(20, 218, 166, 23);

        panel1.add(lblUsername);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 245, 188, 187);
        panel1.add(scrollPane_1);

        table = new JTable();
        table.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        scrollPane_1.setViewportView(table);

        btnLogout = new JButton("LOGOUT CHATTING");
        btnLogout.setBounds(10, 443, 188, 34);
		btnLogout.setForeground(new Color(120, 90, 40));
		btnLogout.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel1.add(btnLogout);

        /**
         * panel2
         */
        JPanel panel2 = new JPanel();
        panel2.setBounds(228, 11, 480, 486);
        contentPane.add(panel2);
        panel2.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 460, 424);
        panel2.add(scrollPane);

        txtAreaChat = new JTextArea();
        txtAreaChat.setEditable(false);
        scrollPane.setViewportView(txtAreaChat);

        txtMessage = new JTextField();
        txtMessage.setBackground(Color.WHITE);
        txtMessage.setBounds(10, 443, 314, 32);
        panel2.add(txtMessage);
        txtMessage.setColumns(10);

        btnSend = new JButton("");
        btnSend.setBackground(Color.WHITE);
        btnSend.setBounds(371, 443, 45, 32);
        btnSend.setIcon(new ImageIcon(ImageConstraints.img_send));
        panel2.add(btnSend);

        btnFile = new JButton("");
        btnFile.setBackground(Color.WHITE);
        btnFile.setBounds(425, 443, 45, 32);
        btnFile.setIcon(new ImageIcon(ImageConstraints.img_file));
        panel2.add(btnFile);

        JPanel panel = new JPanel();
        panel.setBounds(334, 443, 32, 32);
        panel2.add(panel);
    }

    public String getName() {
        return this.username;
    }

    public void setTextArea(String text) {
        txtAreaChat.setText(text);
    }

    public String getTextArea() {
        return txtAreaChat.getText().trim();
    }

    public void setTable(Object[][] online) {
        table.setModel(new DefaultTableModel(online, columnNames));
    }

    public void addSendListener(ActionListener listener) {
        btnSend.addActionListener(listener);
    }

    public String getNameOnline() {
        return table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
    }

    public String getMsg() {
        return txtMessage.getText().trim();
    }

    public void clearMsg() {
        txtMessage.setText("");
    }

    public void addLogoutListener(ActionListener listener) {
        btnLogout.addActionListener(listener);
    }
}
