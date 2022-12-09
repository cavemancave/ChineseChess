
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import java.awt.geom.Rectangle2D;

public class ChessBoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public String msg;
	int x,y;
	public static int pieceWidth = 90;
	public static int startX = 50;
	public static int startY = 50;
	public static int gridWidth = 100;
	public ChessPiece pickupPiece;
	ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();

	public void AddPieces(int[] xs, int y, String[] names, Color color) {
		for (int i = 0; i < xs.length; i++) {
			this.pieces.add(new ChessPiece(xs[i], y, names[i], color, false));
		}

	}

	public int[] myRange(int start, int stop, int step) {

		int size = (stop - start) / step + 1;
		int[] nums = new int[size];
		int num = start;
		for (int i = 0; i < size; i++) {
			nums[i] = num;
			num += step;
		}
		return nums;
	}

	public String[] repeatString(String text, int size) {

		String[] texts = new String[size];
		for (int i = 0; i < size; i++) {
			texts[i] = text;
		}
		return texts;
	}

	public void initPieces() {
		String[] nameArray = { "车", "马", "象", "士", "将" };
		AddPieces(myRange(50, 50 + 100 * nameArray.length - 1, 100), 50, nameArray, Color.RED);

		String[] subNames = Arrays.copyOfRange(nameArray, 0, 4);
		AddPieces(myRange(50 + 100 * (nameArray.length + subNames.length - 1), 50 + 100 * (nameArray.length), -100), 50,
				subNames, Color.RED);
		AddPieces(myRange(150, 750, 600), 250, repeatString("炮", 2), Color.RED);
		AddPieces(myRange(50, 851, 200), 350, repeatString("卒", 5), Color.RED);

	}

	public ChessBoardPanel() {
		msg="";
		x=0;
		y=0;
		initPieces();
		
	}

	public void drawPiece(Graphics g, ChessPiece piece) {
		Color old = g.getColor();
		g.setColor(Color.orange);
		g.fillOval(piece.x - pieceWidth / 2, piece.y - pieceWidth / 2, pieceWidth, pieceWidth);

		g.setColor(Color.RED);
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

		int x = piece.x - metrics.stringWidth(piece.name) / 2;
		int y = piece.y + metrics.getAscent() / 2;

		g.drawString(piece.name, x, y);
		g.setColor(old);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font(Font.SERIF, Font.BOLD, 50));
		g.drawString(this.msg, this.x, this.y);
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

		for (ChessPiece piece : pieces) {
			drawPiece(g, piece);
		}

	}

	public void run() {
		
		addMouseListener (new MyMouse (this));
		addMouseMotionListener (new MyMouseMotion (this));
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
