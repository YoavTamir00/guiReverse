package game;

public class Cell {
    private int row, column;

    public Cell() {
        // Invalid Cell.
        this(-1, -1);
    }

    public Cell(int x, int y) {
        this.row = x;
        this.column = y;
    }

    // Getters
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    // A valid cell must have positive values.
    public boolean isValid() {
        return row >= 0 && column >= 0;
    }

    public boolean equals(Cell other) {
        return this.row == other.getRow() && other.getColumn() == this.column;
    }

    // THis function is mostly for debugging purposes, but the gui sometimes needs it too.
    public String toString() {
        return "(" + row + "," + column + ")";
    }
}