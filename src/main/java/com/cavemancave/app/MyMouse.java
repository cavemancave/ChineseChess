package com.cavemancave.app;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MyMouse implements MouseListener {
	ChessBoardPanel board;

	MyMouse(ChessBoardPanel board) {
		this.board = board;
	}

	public void DoCancel(ChessPiece pickedPiece) {
		pickedPiece.picked = false;
		this.board.gameState.pickedPiece = null;
		this.board.gameState.targetPositions.clear();
	}

	public void DoDropDown(ChessPiece pickedPiece, Point dstPoint) {
		if(dstPoint == null) {
			DoCancel(pickedPiece);
		}
		Point matchedTarget = null;
		for (Point target : this.board.gameState.targetPositions) {
			if (dstPoint == target) {
				matchedTarget = target;
				break;
			}
		}
		if (matchedTarget == null) {
			DoCancel(pickedPiece);
			return;
		}
		ChessPiece dstPiece = this.board.gameState.postionMap[dstPoint.y][dstPoint.x];
		if (dstPiece != null) {
			dstPiece.eaten = true;
		}

		this.board.gameState.postionMap[pickedPiece.position.y][pickedPiece.position.x] = null;
		pickedPiece.position = dstPoint;
		pickedPiece.picked = false;
		this.board.gameState.pickedPiece = null;
		this.board.gameState.targetPositions.clear();
		this.board.gameState.postionMap[dstPoint.y][dstPoint.x] = pickedPiece;
	}
	public void DoPickup(Point gridPoint) {
		if(gridPoint == null ) {
			return;
		}
		ChessPiece selectedPiece = this.board.gameState.postionMap[gridPoint.y][gridPoint.x];
		if (selectedPiece != null) {
			selectedPiece.picked = true;
			this.board.gameState.pickedPiece = selectedPiece;
			this.board.gameState.CalcTargetPositions();
		}
	}
	public void mouseClicked(MouseEvent me) {
		this.board.msg = "Mouse Clicked";
		this.board.x = me.getX();
		this.board.y = me.getY();
		int mouseX = me.getX();
		int mouseY = me.getY();
		Point gridPoint = this.board.getGridPoint(mouseX, mouseY, ChessBoardPanel.gridWidth,
				ChessBoardPanel.pieceWidth / 2);

		ChessPiece pickedPiece = this.board.gameState.pickedPiece;
		if (pickedPiece != null) {
			DoDropDown(pickedPiece, gridPoint);
		} else {
			DoPickup(gridPoint);
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