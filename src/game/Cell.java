package game;

public class Cell {
	private int row, column;

	public Cell() {
		this(-1, -1);
	}

	public Cell(int x, int y) {
		this.row = x;
		this.column = y;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public boolean isValid() {
		return row >= 0 && column >= 0;
	}

	public boolean equals(Cell other) {
		return this.row == other.getRow() && other.getColumn() == this.column;
	}

	public String toString() {
		return "(" + row + "," + column + ")";
	}
}