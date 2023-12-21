package com.cavemancave.app;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class MyMouseMotion implements MouseMotionListener {
		ChessBoardPanel board;

		MyMouseMotion(ChessBoardPanel board) {
			this.board = board;
		}

		public void mouseMoved(MouseEvent me) {
			int mouseX = me.getX();
			int mouseY = me.getY();
			this.board.msg = "Mouse Moved["+mouseX+","+mouseY+"]";
			this.board.x = mouseX;
			this.board.y = mouseY;

			/*
			 * if (this.board.pickupPiece != null) {
			 * 
			 * this.board.pickupPiece.x = mouseX; this.board.pickupPiece.y = mouseY; }
			 */
			//this.board.repaint();
		}


		@Override
		public void mouseDragged(MouseEvent me) {

		}
	}