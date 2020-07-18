package com.view;

import com.DAO.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import static com.constraints.ImageConstraints.img_logo1;

public class SignUpView extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField txtName;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtCnfPass;
	private JButton btnSignUp;
	private JPanel pnlSignIn;

	/**
	 * Create the frame.
	 */
	public SignUpView() {

		initcomponents();
	}

	public void initcomponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 532);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 600, 500);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 11, 293, 462);
		lblLogo.setIcon(new ImageIcon(img_logo1));
		panel.add(lblLogo);

		JLabel lblTitle = new JLabel("SIGN UP CHATTING");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblTitle.setForeground(new Color(120, 90, 40));
		lblTitle.setBounds(357, 30, 201, 40);
		panel.add(lblTitle);

		JLabel lblName = new JLabel("Enter Your Name:");
		lblName.setBounds(330, 83, 150, 30);
		lblName.setForeground(new Color(120, 90, 40));
		lblName.setFont(new Font("Verdana", Font.PLAIN, 15));
		panel.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(330, 117, 250, 30);

		panel.add(txtName);
		txtName.setColumns(10);

		JLabel lblUsername = new JLabel("Enter Your Username:");
		lblUsername.setBounds(330, 158, 150, 30);
		lblUsername.setForeground(new Color(120, 90, 40));
		lblUsername.setFont(new Font("Verdana", Font.PLAIN, 15));
		panel.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setBounds(330, 199, 250, 30);
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Enter Your Password");
		lblPassword.setBounds(330, 240, 150, 30);
		lblPassword.setForeground(new Color(120, 90, 40));
		lblPassword.setFont(new Font("Verdana", Font.PLAIN, 15));
		panel.add(lblPassword);

		JLabel lblCnfPass = new JLabel("Confirm Password");
		lblCnfPass.setBounds(330, 322, 150, 30);
		lblCnfPass.setForeground(new Color(120, 90, 40));
		lblCnfPass.setFont(new Font("Verdana", Font.PLAIN, 15));
		panel.add(lblCnfPass);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(330, 281, 250, 30);
		panel.add(txtPassword);
		
		txtCnfPass = new JPasswordField();
		txtCnfPass.setBounds(330, 363, 250, 30);
		panel.add(txtCnfPass);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(new Color(120, 90, 40));
		btnSignUp.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnSignUp.setBounds(406, 435, 100, 30);
		panel.add(btnSignUp);

		JLabel lblSignIn = new JLabel("You have an account ? Log in now.");
		lblSignIn.setBounds(0, 0, 207, 20);
		lblSignIn.setForeground(Color.BLUE);
		lblSignIn.setFont(new Font("Verdana", Font.PLAIN, 10));

		pnlSignIn = new JPanel();
		pnlSignIn.setLayout(null);
		pnlSignIn.setBounds(351, 404, 207, 20);
		pnlSignIn.add(lblSignIn);
		panel.add(pnlSignIn);

	}



	public String getName() {
		return txtName.getText().toString();
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public boolean confirmPassword() {
		if (String.copyValueOf(txtPassword.getPassword()).equals(String.copyValueOf(txtCnfPass.getPassword()))) {
			return true;
		}
		return false;
	}

	public User getUser() {
		return new User(txtUsername.getText().trim(), txtUsername.getText().trim(), String.copyValueOf(txtPassword.getPassword()));
	}

	public void addSignupListener(ActionListener listener) {
		btnSignUp.addActionListener(listener);
	}

	public boolean checkEmpty() {
		if (txtName.getText().equals("") || txtPassword.getPassword().equals("") || txtPassword.getPassword().equals("")) {
			return true;
		}
		return false;
	}

	public void addLoginListener(MouseListener listener) {
		pnlSignIn.addMouseListener(listener);
	}
}
