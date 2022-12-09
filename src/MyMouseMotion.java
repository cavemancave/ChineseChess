import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class MyMouseMotion implements MouseMotionListener {
		ChessBoardPanel board;

		MyMouseMotion(ChessBoardPanel board) {
			this.board = board;
		}

		public void mouseMoved(MouseEvent me) {
			this.board.msg = "Mouse Moved";
			this.board.x = me.getX();
			this.board.y = me.getY();
			int mouseX = me.getX();
			int mouseY = me.getY();
			if (this.board.pickupPiece != null) {
				
				this.board.pickupPiece.x = mouseX;
				this.board.pickupPiece.y = mouseY;
			}
			this.board.repaint();
		}


		@Override
		public void mouseDragged(MouseEvent me) {

		}
	}