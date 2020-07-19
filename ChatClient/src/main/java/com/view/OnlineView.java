package com.view;


import com.constraints.ImageConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OnlineView extends JFrame {

	private JPanel contentPane;
	private String[] columnNames = new String[]{"ONLINE","",""};
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
		List<String> abc = new ArrayList<String>();
		
		JLabel lblUsername = new JLabel("----");
		lblUsername.setBounds(34, 11, 200, 52);
		lblUsername.setText(username);
		lblUsername.setFont(new Font("Roboto", Font.ITALIC,30));
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
		btnLogout.setBounds(349, 11, 55, 55);
		btnLogout.setIcon(new ImageIcon(ImageConstraints.img_logout));
		contentPane.add(btnLogout);
	}

	public void setTable(Object[][] online) {
		table.setModel(new DefaultTableModel(online, columnNames));
	}
}
