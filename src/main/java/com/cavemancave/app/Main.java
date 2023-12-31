package com.cavemancave.app;

import javax.swing.JFrame;

public class Main {

	public static final int WIDTH = 600;
	public static final int HEIGHT = 650;

	public static void main(String[] args) {
		JFrame win = new JFrame();
		win.setVisible(true);
		win.setSize(WIDTH, HEIGHT);
		win.setDefaultCloseOperation(3);
		win.setLocationRelativeTo(null);

		ChessBoardPanel panle = new ChessBoardPanel();
		win.add(panle);

		panle.run();
	}

}
