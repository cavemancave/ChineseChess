package com.cavemancave.app;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MyMouse implements MouseListener {
		ChessBoardPanel board;

		MyMouse(ChessBoardPanel board) {
			this.board = board;
		}
		public boolean MoveCheckCommon(Point start, Point end) {
			if(end == null) return false;
			if((start.x == end.x) && (start.y == end.y)) return false;
			if(start.x<0 || start.x>8 || start.y<0 || start.y>9 ||
					end.x<0 || end.x>8 || end.y<0 || end.y>9 	) {
				return false;
			}
			return true;
		}
		public int GetCrossCount(Point start, Point end) {
			int crossCount = 0;
			if(start.x == end.x) {
				int step = (start.y<end.y)?1:-1;
				for(int i=start.y+step;i!=end.y;i+=step) {
					ChessPiece curGrid = this.board.gameState.postionMap[i][start.x];
					if((curGrid!=null) && (curGrid.eaten!=true)) crossCount++;
				}
			}else if(start.y == end.y) {
				int step = (start.x<end.x)?1:-1;
				for(int i=start.x+step;i!=end.x;i+=step) {
					ChessPiece curGrid = this.board.gameState.postionMap[start.y][i];
					if((curGrid!=null) && (curGrid.eaten!=true)) crossCount++;
				}
			}
			return crossCount;
		}
		public boolean MoveCheckPao(Point start, Point end) {
			if((start.x == end.x) ^ (start.y == end.y) ){
				return true;
			} 
			return false;
		}
		public boolean MoveCheck(String type, Point start, Point end) {
			if(end == null) {
				return false;
			}
			if(!MoveCheckCommon(start, end)) {
				return false;
			}
			switch(type) {
			case "炮":return MoveCheckPao(start, end);
			}
			return true;
		}
		public boolean EatenCheck(ChessPiece pickedPiece, ChessPiece eatingPiece) {
			if(pickedPiece.color== eatingPiece.color ) return false;
			if(pickedPiece.type == "炮") {
				int crossCount = GetCrossCount(pickedPiece.position, eatingPiece.position);
				if(crossCount != 1) {
					return false;
				}
			}
			return true;
		}
		public void DoDropDown(ChessPiece pickedPiece, Point dstPoint) {
			if(!MoveCheck(pickedPiece.type, pickedPiece.position, dstPoint)) {
				pickedPiece.picked = false;
				this.board.gameState.pickedPiece = null;
				return;
			}
			ChessPiece dstPiece = this.board.gameState.postionMap[dstPoint.y][dstPoint.x];
			if(dstPiece != null) {
				if(!EatenCheck(pickedPiece, dstPiece)) {
					pickedPiece.picked = false;
					this.board.gameState.pickedPiece = null;
					return;
				}
				dstPiece.eaten = true;
			}
			this.board.gameState.postionMap[pickedPiece.position.y][pickedPiece.position.x] = null;
			pickedPiece.position = dstPoint;
			pickedPiece.picked = false;
			this.board.gameState.pickedPiece = null;
			this.board.gameState.postionMap[dstPoint.y][dstPoint.x] = pickedPiece;
		}
		public void mouseClicked(MouseEvent me) {
			this.board.msg = "Mouse Clicked";
			this.board.x = me.getX();
			this.board.y = me.getY();
			int mouseX = me.getX();
			int mouseY = me.getY();
			Point gridPoint = this.board.getGridPoint(mouseX, mouseY, ChessBoardPanel.gridWidth, ChessBoardPanel.pieceWidth/2);

			ChessPiece selectedPiece = this.board.gameState.pickedPiece;
			if(selectedPiece != null) {
				DoDropDown(selectedPiece, gridPoint);
			}else {
				ChessPiece selectedPiece2 = this.board.gameState.postionMap[gridPoint.y][gridPoint.x];
				if(selectedPiece2 != null) {
					selectedPiece2.picked = true;
					this.board.gameState.pickedPiece = selectedPiece2;
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