package com.cavemancave.app;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login login = new Login();
		login.initUI();
	}

	public void initUI() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setTitle("登录（未注册用户将直接注册）");
		frame.setSize(400, 250);
		frame.setLayout(new FlowLayout());

		JLabel lab_name = new JLabel("UserName: ");
		frame.add(lab_name);
		JTextField txt_name = new JTextField(25);
		Dimension dim_size = new Dimension(300, 30);
		txt_name.setPreferredSize(dim_size);
		frame.add(txt_name);

		JLabel lab_pass = new JLabel("Password: ");
		frame.add(lab_pass);
		JPasswordField txt_pass = new JPasswordField(25);
		txt_pass.setPreferredSize(dim_size);
		frame.add(txt_pass);

		JButton button_sign = new JButton("Sign in (Sign up)");
		frame.add(button_sign);
		frame.setVisible(true);

		LoginAction action = new LoginAction(frame, txt_name, txt_pass);
		button_sign.addActionListener(action);
	}

}
