package com.cavemancave.app;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GameState {
	Color activeColor;
	boolean selected;
	Point start;
	Point end;
	List<Point> targetPositions;
	public ChessPiece[][] postionMap;
	public ChessPiece pickedPiece;
	boolean finished;
	public GameState(Color activeColor) {
		this.activeColor = activeColor;
		this.selected = false;
		this.start = new Point(0,0);
		this.end = new Point(0,0);
		this.targetPositions = new ArrayList<>();
		this.postionMap = new ChessPiece[10][9];
		this.pickedPiece = null;
	}
}
