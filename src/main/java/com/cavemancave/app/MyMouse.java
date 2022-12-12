package com.cavemancave.app;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MyMouse implements MouseListener {
		ChessBoardPanel board;

		MyMouse(ChessBoardPanel board) {
			this.board = board;
		}

		public ChessPiece findPieceInPoint(int x, int y) {
			for(ChessPiece piece:this.board.pieces) {
				if(piece == this.board.pickupPiece) {
					continue;
				}
				if(piece.InRange(x, y)) {
					return piece;
				}
			}
			return null;
		}
		
		public void mouseClicked(MouseEvent me) {
			this.board.msg = "Mouse Clicked";
			this.board.x = me.getX();
			this.board.y = me.getY();
			int mouseX = me.getX();
			int mouseY = me.getY();
			if (this.board.pickupPiece == null) {
				ChessPiece piece = findPieceInPoint(mouseX, mouseY);
				if( piece != null) {
					this.board.pickupPiece = piece;
					piece.pickUp = true;
				}
			} else {
				ChessPiece piece = findPieceInPoint(mouseX, mouseY);
				if( piece != null) {
					this.board.pickupPiece.x = piece.x;
					this.board.pickupPiece.y = piece.y;
					this.board.pickupPiece.pickUp = false;
					this.board.pieces.remove(piece);
					this.board.pickupPiece = null;
				}else {
					this.board.pickupPiece.pickUp = false;
					this.board.pickupPiece = null;
				}
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