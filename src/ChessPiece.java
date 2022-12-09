import java.awt.Color;
public class ChessPiece {
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
}
