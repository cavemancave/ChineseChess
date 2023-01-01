package com.cavemancave.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginAction implements ActionListener {
	private JFrame frame;
	private JTextField txt_name;
	private JPasswordField txt_pass;
	public static void main(String[] args) {
		LoginAction test = new LoginAction();
		test.MesseageBox("Wrong password.");
	}
	public LoginAction(JFrame frame, JTextField txt_name, JPasswordField txt_pass) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		this.txt_name = txt_name;
		this.txt_pass = txt_pass;
	}
	public LoginAction() {

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.txt_name.getText().equals("zhangsan")) {
			char[] passwd = this.txt_pass.getPassword();
			if("password".equals(new String(passwd))) {
				Hall hall = new Hall(txt_name);
				hall.InitUI();
				frame.dispose();
			}
			else {
				MesseageBox("Wrong password.");
			}

		}
	}
	public void MesseageBox(String txt) {
		MesseageBox(txt, 300, 100);
	}
	
	public void MesseageBox(String txt, int width, int height) {
		final JFrame loginErr = new JFrame(txt);
		loginErr.setSize(width,height);
		JLabel labErr = new JLabel(txt);
		labErr.setHorizontalAlignment(JLabel.CENTER);
		labErr.setPreferredSize(new Dimension(width,30));
		JPanel jp_txt = new JPanel();
		jp_txt.add(labErr);
		loginErr.add(jp_txt, BorderLayout.CENTER);
		
		JButton btnErr = new JButton("OK");
		btnErr.setPreferredSize(new Dimension(100,20));
		JPanel jp_btn = new JPanel();
		jp_btn.add(btnErr);
		loginErr.add(jp_btn,BorderLayout.SOUTH);
		btnErr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginErr.dispose();
			}
		});
		
		loginErr.setVisible(true);
	}
	
	
}
