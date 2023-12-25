package com.cavemancave.app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public String msg;
	int x, y;
	public static int gridWidth = 50;
	public static int pieceWidth = (int) (gridWidth * 0.9);
	public static int startX = gridWidth;
	public static int startY = gridWidth;
	public static int fontSize = gridWidth / 2;
	GameState gameState;
	List<ChessPiece> pieces;

	public void initPieces() {
		this.pieces = new ArrayList<>();
		this.pieces.add(new ChessPiece("车", Color.RED, new Point(0, 0)));
		this.pieces.add(new ChessPiece("马", Color.RED, new Point(1, 0)));
		this.pieces.add(new ChessPiece("相", Color.RED, new Point(2, 0)));
		this.pieces.add(new ChessPiece("士", Color.RED, new Point(3, 0)));
		this.pieces.add(new ChessPiece("将", Color.RED, new Point(4, 0)));
		this.pieces.add(new ChessPiece("士", Color.RED, new Point(5, 0)));
		this.pieces.add(new ChessPiece("相", Color.RED, new Point(6, 0)));
		this.pieces.add(new ChessPiece("马", Color.RED, new Point(7, 0)));
		this.pieces.add(new ChessPiece("车", Color.RED, new Point(8, 0)));
		this.pieces.add(new ChessPiece("炮", Color.RED, new Point(1, 2)));
		this.pieces.add(new ChessPiece("炮", Color.RED, new Point(7, 2)));
		this.pieces.add(new ChessPiece("兵", Color.RED, new Point(0, 3)));
		this.pieces.add(new ChessPiece("兵", Color.RED, new Point(2, 3)));
		this.pieces.add(new ChessPiece("兵", Color.RED, new Point(4, 3)));
		this.pieces.add(new ChessPiece("兵", Color.RED, new Point(6, 3)));
		this.pieces.add(new ChessPiece("兵", Color.RED, new Point(8, 3)));
		this.pieces.add(new ChessPiece("车", Color.BLACK, new Point(0, 9)));
		this.pieces.add(new ChessPiece("马", Color.BLACK, new Point(1, 9)));
		this.pieces.add(new ChessPiece("相", Color.BLACK, new Point(2, 9)));
		this.pieces.add(new ChessPiece("士", Color.BLACK, new Point(3, 9)));
		this.pieces.add(new ChessPiece("将", Color.BLACK, new Point(4, 9)));
		this.pieces.add(new ChessPiece("士", Color.BLACK, new Point(5, 9)));
		this.pieces.add(new ChessPiece("相", Color.BLACK, new Point(6, 9)));
		this.pieces.add(new ChessPiece("马", Color.BLACK, new Point(7, 9)));
		this.pieces.add(new ChessPiece("车", Color.BLACK, new Point(8, 9)));
		this.pieces.add(new ChessPiece("炮", Color.BLACK, new Point(1, 7)));
		this.pieces.add(new ChessPiece("炮", Color.BLACK, new Point(7, 7)));
		this.pieces.add(new ChessPiece("兵", Color.BLACK, new Point(0, 6)));
		this.pieces.add(new ChessPiece("兵", Color.BLACK, new Point(2, 6)));
		this.pieces.add(new ChessPiece("兵", Color.BLACK, new Point(4, 6)));
		this.pieces.add(new ChessPiece("兵", Color.BLACK, new Point(6, 6)));
		this.pieces.add(new ChessPiece("兵", Color.BLACK, new Point(8, 6)));
	}

	public void initPositionMap(List<ChessPiece> pieces) {
		for (ChessPiece piece : pieces) {
			gameState.postionMap[piece.position.y][piece.position.x] = piece;
		}
	}

	public ChessBoardPanel() {
		msg = "";
		x = 0;
		y = 0;
		initPieces();
		gameState = new GameState(Color.RED);
		initPositionMap(this.pieces);
	}

	public Point getGridPoint(int x, int y, int gridWidth, int radius) {
		if(x>gridWidth*9 || y> gridWidth*10) {
			return null;
		}
		int halfWidth = gridWidth / 2;
		int gridX = (x + halfWidth) / gridWidth;
		int gridY = (y + halfWidth) / gridWidth;

		double distance = Math.sqrt(Math.pow(gridX * gridWidth - x, 2) + Math.pow(gridY * gridWidth - y, 2));
		if (distance > radius) {
			return null;
		}
		Point p = new Point(gridX - 1, gridY - 1);
		return p;
	}

	public void drawStringCenter(Graphics g, String txt, Color c, int txtX, int txtY) {

		FontMetrics metrics = g.getFontMetrics(g.getFont());

		// https://stackoverflow.com/a/1055884/5628643
//	    ---
//	    ^
//	    |  leading
//	    |
//	    -- 
//	    ^              Y     Y
//	    |               Y   Y
//	    |                Y Y
//	    |  ascent         Y     y     y 
//	    |                 Y      y   y
//	    |                 Y       y y
//	    -- baseline ______Y________y_________
//	    |                         y                
//	    v  descent              yy
//	    --	

		int x = txtX - metrics.stringWidth(txt) / 2;
		int y = txtY + metrics.getAscent() / 2;
		Color old = g.getColor();
		g.setColor(c);
		g.drawString(txt, x, y);
		g.setColor(old);
	}

	public Point GetOvalCenter(Point gridPosition) {
		int drawX = (gridPosition.x + 1) * gridWidth;
		int drawY = (gridPosition.y + 1) * gridWidth;
		Point center = new Point(drawX, drawY);
		return center;
	}

	public void fillOval(Graphics g, Point center, int radias) {
		g.fillOval(center.x - radias / 2, center.y - radias / 2, radias, radias);
	}

	public void drawOval(Graphics g, Point center, int radias) {
		g.drawOval(center.x - radias / 2, center.y - radias / 2, radias, radias);
	}

	public void drawPiece(Graphics g, ChessPiece piece) {
		if (piece.eaten)
			return;
		Color old = g.getColor();
		g.setColor(Color.orange);
		int drawX = (piece.position.x + 1) * gridWidth;
		int drawY = (piece.position.y + 1) * gridWidth;
		Point center = new Point(drawX, drawY);
		fillOval(g, center, pieceWidth);
		drawStringCenter(g, piece.type, piece.color, drawX, drawY);
		if (piece.picked == true) {
			g.setColor(Color.RED);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			drawOval(g, center, pieceWidth);
		}
		g.setColor(old);
	}

	public void drawTarget(Graphics g, List<Point> targetPositions) {
		Color old = g.getColor();
		g.setColor(Color.DARK_GRAY);
		int targetPointRadias = (int) (gridWidth * 0.2);
		for (Point position : targetPositions) {
			Point center = GetOvalCenter(position);
			fillOval(g, center, targetPointRadias);
		}
		g.setColor(old);
	}

	public void drawLine(Graphics g) {
		g.drawRect(startX, startY, 8 * gridWidth, 9 * gridWidth);
		// horizon
		for (int i = 1; i < 9; i += 1) {
			g.drawLine(startX, startY + gridWidth * i, startX + 8 * gridWidth, startY + gridWidth * i);
		}
		// vertical
		for (int i = 1; i < 8; i += 1) {
			// up
			g.drawLine(startX + gridWidth * i, startY, startX + gridWidth * i, startY + 4 * gridWidth);
			// down
			g.drawLine(startX + gridWidth * i, startY + 5 * gridWidth, startX + gridWidth * i, startY + 9 * gridWidth);
		}
		//
		g.drawLine(startX + 3 * gridWidth, startY, startX + 5 * gridWidth, startY + gridWidth * 2);
		g.drawLine(startX + 3 * gridWidth, startY + gridWidth * 2, startX + 5 * gridWidth, startY);
		g.drawLine(startX + 3 * gridWidth, startY + gridWidth * 7, startX + 5 * gridWidth, startY + gridWidth * 9);
		g.drawLine(startX + 3 * gridWidth, startY + gridWidth * 9, startX + 5 * gridWidth, startY + gridWidth * 7);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font(Font.SERIF, Font.BOLD, ChessBoardPanel.fontSize));
		g.drawString(this.msg, this.x, this.y);
		drawStringCenter(g, "楚  河", Color.GRAY, startX + gridWidth * 2, startY + (int) (gridWidth * 4.5));
		drawStringCenter(g, "汉  界", Color.GRAY, startX + gridWidth * 6, startY + (int) (gridWidth * 4.5));

		drawLine(g);
		for (ChessPiece piece : pieces) {
			drawPiece(g, piece);
		}
		drawTarget(g, this.gameState.targetPositions);
	}

	public void run() {

		addMouseListener(new MyMouse(this));
		addMouseMotionListener(new MyMouseMotion(this));
		repaint();
//		Timer timer = new Timer();
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				repaint();
//			}
//		};
//		timer.schedule(task, 1000, 15);
	}

}
