package com.cavemancave.app;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MyMouse implements MouseListener {
		ChessBoardPanel board;

		MyMouse(ChessBoardPanel board) {
			this.board = board;
		}

		public void mouseClicked(MouseEvent me) {
			this.board.msg = "Mouse Clicked";
			this.board.x = me.getX();
			this.board.y = me.getY();
			int mouseX = me.getX();
			int mouseY = me.getY();
			if (this.board.pickupPiece == null) {
				for (ChessPiece piece : this.board.pieces) {
					int distance = (int) Math.sqrt(Math.pow(mouseX - piece.x, 2) + Math.pow(mouseY - piece.y, 2));
					if (distance < ChessBoardPanel.pieceWidth) {
						this.board.pickupPiece = piece;
						piece.pickUp = true;
					}
				}
			} else {
				this.board.pickupPiece.pickUp = false;
				this.board.pickupPiece = null;
			}
			this.board.repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}