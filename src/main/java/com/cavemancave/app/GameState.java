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
		this.start = new Point(0, 0);
		this.end = new Point(0, 0);
		this.targetPositions = new ArrayList<>();
		this.postionMap = new ChessPiece[10][9];
		this.pickedPiece = null;
	}

	public void CalcTargetPositions() {
		switch (this.pickedPiece.type) {
		case "炮":
			CalcTargetPositionPao();
			break;
		case "兵":
			CalcTargetPositionBing();
			break;
		default:
			this.targetPositions.clear();
		}
	}
	public void CalcTargetPositionPao() {
		Point curPosition = this.pickedPiece.position;
		int crossCnt = 0;
		int x = curPosition.x;
		int y= curPosition.y;
		for(x=curPosition.x+1;x<=8;x++) {
			if(!CheckTargetPao(crossCnt, x, y)) break;
		}
		crossCnt = 0;
		x = curPosition.x;
		y= curPosition.y;
		for(x=curPosition.x-1;x>=0;x--) {
			if(!CheckTargetPao(crossCnt, x, y)) break;
		}
		crossCnt = 0;
		x = curPosition.x;
		y= curPosition.y;
		for(y=curPosition.y+1;y<=9;y++) {
			if(!CheckTargetPao(crossCnt, x, y)) break;
		}
		crossCnt = 0;
		x = curPosition.x;
		y= curPosition.y;
		for(y=curPosition.y-1;y>=0;y--) {
			if(!CheckTargetPao(crossCnt, x, y)) break;
		}
	}
	
	private boolean CheckTargetPao(int crossCnt, int x, int y) {
		ChessPiece dstPiece = this.postionMap[y][x];
		if(dstPiece == null) {
			this.targetPositions.add(new Point(x, y));
		}else if(crossCnt==0) {
			crossCnt++;
		}else if(crossCnt == 1) {
			if(dstPiece.color != this.pickedPiece.color) {
				this.targetPositions.add(new Point(x, y));
				return false;
			}else {
				return false;
			}
		}
		return true;
	}
	public void CalcTargetPositionBing() {
		Point curPosition = this.pickedPiece.position;
		if (this.pickedPiece.color == Color.RED) {
			if (curPosition.y <= 4) {
				this.targetPositions.add(new Point(curPosition.x, curPosition.y + 1));
			} else {
				if (curPosition.y < 9)
					this.targetPositions.add(new Point(curPosition.x, curPosition.y + 1));
				if (curPosition.x > 0)
					this.targetPositions.add(new Point(curPosition.x - 1, curPosition.y));
				if (curPosition.x < 8)
					this.targetPositions.add(new Point(curPosition.x + 1, curPosition.y));
			}
		} else {
			if (curPosition.y >= 5) {
				this.targetPositions.add(new Point(curPosition.x, curPosition.y - 1));
			} else {
				if (curPosition.y > 0)
					this.targetPositions.add(new Point(curPosition.x, curPosition.y - 1));
				if (curPosition.x > 0)
					this.targetPositions.add(new Point(curPosition.x - 1, curPosition.y));
				if (curPosition.x < 8)
					this.targetPositions.add(new Point(curPosition.x + 1, curPosition.y));
			}
		}
	}
}
