package com.cavemancave.app;
import java.awt.Color;
public class ChessPiece {
    static int diameter;
	int x;
	int y;
	String name;
	Color color;
	boolean pickUp;
	public ChessPiece(int x, int y, String name, Color color, boolean pickUp) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.color = color;
		this.pickUp = pickUp;
	}
	public boolean InRange(int x, int y) {
		int distance = (int) Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
		return (distance <= diameter);
	}
}
