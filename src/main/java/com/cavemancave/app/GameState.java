package com.cavemancave.app;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameState {
	//static final Logger logger = LoggerFactory.getLogger(GameState.class);
	Color activeColor;
	boolean selected;
	Point moveStart;
	Point moveEnd;
	List<Point> targetPositions;
	public ChessPiece[][] postionMap;
	public ChessPiece pickedPiece;
	boolean finished;
	Color LoseColor;
	
	public GameState(Color activeColor) {
		this.activeColor = activeColor;
		this.selected = false;
		this.moveStart = null;
		this.moveEnd = null;
		this.targetPositions = new ArrayList<>();
		this.postionMap = new ChessPiece[10][9];
		this.pickedPiece = null;
	}
	public boolean InBoard(Point p) {
		return !(p.x < 0 || p.x > 8 || p.y < 0 || p.y > 9);
	}
	public boolean InSelfBoard(Point p, Color color) {
		if(color == Color.RED) {
			return !(p.x < 0 || p.x > 8 || p.y < 0 || p.y > 4);
		}
		return !(p.x < 0 || p.x > 8 || p.y < 5 || p.y > 9);
	}
	public boolean InPalace(Point p, Color color) {
		if(color == Color.RED) {
			return !(p.x < 3 || p.x > 5 || p.y < 0 || p.y > 2);
		}
		return !(p.x < 3 || p.x > 5 || p.y < 7 || p.y > 9);
	}
	public ChessPiece GetPiece(Point p) {
		return this.postionMap[p.y][p.x];
	}
	public void ReveseActiveColor() {
		if (this.activeColor == Color.RED) {
			this.activeColor = Color.BLACK;
		} else {
			this.activeColor = Color.RED;
		}
	}

	public void CalcTargetPositions() {
		switch (this.pickedPiece.type) {
		case "兵":
			CalcTargetPositionBing();
			break;
		case "炮":
			CalcTargetPositionPao();
			break;
		case "车":
			CalcTargetPositionChe();
			break;
		case "马":
			CalcTargetPositionMa();
			break;
		case "相":
			CalcTargetPositionXiang();
			break;
		case "士":
			CalcTargetPositionShi();
			break;
		case "将":
			CalcTargetPositionJiang();
			break;

		default:
			this.targetPositions.clear();
		}
	}

	private void CalcTargetPositionJiang() {
		Point curPosition = this.pickedPiece.position;
		int x = curPosition.x;
		int y = curPosition.y;
		
		// 对将
		int step = (this.pickedPiece.color == Color.RED)?1:-1;
		for(int i=step;(y+i<=9)&&(y+i>=0);i+=step) {
			Point dstPoint = new Point(x, y+i);
			ChessPiece dstPiece = this.GetPiece(dstPoint);
			if ((dstPiece != null) && (dstPiece.type == "将")) {
				this.targetPositions.add(dstPoint);
				return;
			}
		}
		Point[] dst = new Point[4];
		dst[0] = new Point(0, -1);
		dst[1] = new Point(1, 0);
		dst[2] = new Point(0, 1);
		dst[3] = new Point(-1, 0);


		for (int i = 0; i < 4; i++) {
			
			int dstX = x + dst[i].x;
			int dstY = y + dst[i].y;
			Point dstPoint = new Point(dstX, dstY);

			if(!InPalace(dstPoint, this.pickedPiece.color)) continue;
			ChessPiece dstPiece = this.GetPiece(dstPoint);
			if ((dstPiece != null) && (dstPiece.color == this.pickedPiece.color)) {
				continue;
			}

			this.targetPositions.add(dstPoint);
		}
		
	}

	private void CalcTargetPositionShi() {
		Point curPosition = this.pickedPiece.position;
		int x = curPosition.x;
		int y = curPosition.y;

		Point[] dst = new Point[4];
		dst[0] = new Point(-1, -1);
		dst[1] = new Point(1, -1);
		dst[2] = new Point(1, 1);
		dst[3] = new Point(-1, 1);


		for (int i = 0; i < 4; i++) {
			
			int dstX = x + dst[i].x;
			int dstY = y + dst[i].y;
			Point dstPoint = new Point(dstX, dstY);

			if(!InPalace(dstPoint, this.pickedPiece.color)) continue;
			ChessPiece dstPiece = this.GetPiece(dstPoint);
			if ((dstPiece != null) && (dstPiece.color == this.pickedPiece.color)) {
				continue;
			}

			this.targetPositions.add(dstPoint);
		}

	}

	private void CalcTargetPositionXiang() {
		Point curPosition = this.pickedPiece.position;
		int x = curPosition.x;
		int y = curPosition.y;

		Point[] blocks = new Point[4];
		blocks[0] = new Point(-1, -1);
		blocks[1] = new Point(1, -1);
		blocks[2] = new Point(1, 1);
		blocks[3] = new Point(-1, 1);

		Point[] dst = new Point[4];
		dst[0] = new Point(-2, -2);
		dst[1] = new Point(2, -2);
		dst[2] = new Point(2, 2);
		dst[3] = new Point(-2, 2);

		for (int i = 0; i < 4; i++) {
			int blockX = x + blocks[i ].x;
			int blockY = y + blocks[i].y;
			Point blockPoint = new Point(blockX, blockY);

			if(!InBoard(blockPoint)) continue;
			ChessPiece blockPiece = this.GetPiece(blockPoint);
			if (blockPiece != null) {
				continue;
			}
			int dstX = x + dst[i].x;
			int dstY = y + dst[i].y;
			Point dstPoint = new Point(dstX, dstY);

			if(!InSelfBoard(dstPoint, this.pickedPiece.color)) continue;
			ChessPiece dstPiece = this.GetPiece(dstPoint);
			if ((dstPiece != null) && (dstPiece.color == this.pickedPiece.color)) {
				continue;
			}

			this.targetPositions.add(dstPoint);
		}

	}

	private void CalcTargetPositionMa() {
		//logger.info("enter CalcTargetPositionMa.");
		Point curPosition = this.pickedPiece.position;
		int x = curPosition.x;
		int y = curPosition.y;
		//logger.info("cur point ({},{}).", x, y);
		Point[] blocks = new Point[4];
		blocks[0] = new Point(-1, 0);
		blocks[1] = new Point(0, 1);
		blocks[2] = new Point(1, 0);
		blocks[3] = new Point(0, -1);

		Point[] dst = new Point[8];
		dst[0] = new Point(-2, -1);
		dst[1] = new Point(-2, 1);
		dst[2] = new Point(-1, 2);
		dst[3] = new Point(1, 2);
		dst[4] = new Point(2, 1);
		dst[5] = new Point(2, -1);
		dst[6] = new Point(1, -2);
		dst[7] = new Point(-1, -2);

		for (int i = 0; i < 8; i++) {
			int blockX = x + blocks[i / 2].x;
			int blockY = y + blocks[i / 2].y;
			Point blockPoint = new Point(blockX, blockY);
			//logger.info("Test block point ({},{}).", blockX, blockY);
			if(!InBoard(blockPoint)) continue;
			ChessPiece blockPiece = this.GetPiece(blockPoint);
			if (blockPiece != null) {
				continue;
			}
			int dstX = x + dst[i].x;
			int dstY = y + dst[i].y;
			Point dstPoint = new Point(dstX, dstY);
			//logger.info("Test dst point ({},{}).", dstX, dstY);
			if(!InBoard(dstPoint)) continue;
			ChessPiece dstPiece = this.GetPiece(dstPoint);
			if ((dstPiece != null) && (dstPiece.color == this.pickedPiece.color)) {
				continue;
			}
			//logger.info("add dst point.");
			this.targetPositions.add(dstPoint);
		}
	}

	private void CalcTargetPositionChe() {
		Point curPosition = this.pickedPiece.position;
		int x = curPosition.x;
		int y = curPosition.y;
		for (x = curPosition.x + 1; x <= 8; x++) {
			if (CheckTargetChe(x, y))
				break;
		}
		for (x = curPosition.x - 1; x >= 0; x--) {
			if (CheckTargetChe(x, y))
				break;
		}
		x = curPosition.x;
		for (y = curPosition.y + 1; y <= 9; y++) {
			if (CheckTargetChe(x, y))
				break;
		}
		for (y = curPosition.y - 1; y >= 0; y--) {
			if (CheckTargetChe(x, y))
				break;
		}
	}

	// return true if need break
	private boolean CheckTargetChe(int x, int y) {
		ChessPiece dstPiece = this.postionMap[y][x];
		if (dstPiece == null) {
			this.targetPositions.add(new Point(x, y));
			return false;
		}
		if (dstPiece.color != this.pickedPiece.color) {
			this.targetPositions.add(new Point(x, y));
		}
		return true;
	}

	public void CalcTargetPositionPao() {
		Point curPosition = this.pickedPiece.position;
		int[] crossCnt = new int[] { 0 };
		int x = curPosition.x;
		int y = curPosition.y;
		for (x = curPosition.x + 1; x <= 8; x++) {
			if (CheckTargetPao(crossCnt, x, y))
				break;
		}
		crossCnt = new int[] { 0 };
		for (x = curPosition.x - 1; x >= 0; x--) {
			if (CheckTargetPao(crossCnt, x, y))
				break;
		}
		crossCnt = new int[] { 0 };
		x = curPosition.x;
		for (y = curPosition.y + 1; y <= 9; y++) {
			if (CheckTargetPao(crossCnt, x, y))
				break;
		}
		crossCnt = new int[] { 0 };
		for (y = curPosition.y - 1; y >= 0; y--) {
			if (CheckTargetPao(crossCnt, x, y))
				break;
		}
	}

	// return true if need break
	private boolean CheckTargetPao(int[] crossCnt, int x, int y) {
		ChessPiece dstPiece = this.postionMap[y][x];
		if (dstPiece == null) {
			if (crossCnt[0] == 0) {
				this.targetPositions.add(new Point(x, y));
			}
			return false;
		}
		crossCnt[0]++;
		if (crossCnt[0] == 1)
			return false;

		if (dstPiece.color != this.pickedPiece.color) {
			this.targetPositions.add(new Point(x, y));
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
