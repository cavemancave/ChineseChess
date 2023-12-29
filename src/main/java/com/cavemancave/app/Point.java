package com.cavemancave.app;

public class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public boolean equals(Point test) {
		return (this.x == test.x) && (this.y == test.y);
	}
}
