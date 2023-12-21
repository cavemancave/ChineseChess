package com.cavemancave.app;
import java.awt.Color;

public class ChessPiece {
	Color color; //Red / Black
	String type; //Che,Ma,Xiang,Shi,Jiang,Pao,Bing
	boolean picked;
	boolean eaten;
	public Point position;

	public ChessPiece(String type, Color color, Point position) {
		this.type = type;
		this.color = color;
		this.position = position;
		this.picked = false;
		this.eaten = false;
	}
}
