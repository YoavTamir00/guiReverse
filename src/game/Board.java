package game;

import constants.ConstantValues;
import constants.Tile;

public class Board {
    // with numTaken - we can check if the board is full in constant time.
    int numTaken, dimension;
    private Tile[][] board;

    public Board() {
        this(ConstantValues.BOARD_DEFAULT_SIZE);
    }

    public Board(int dimension) {
        initializeBoard(dimension);
    }

    // Copy Constructor
    public Board(Board copy) {
        // (Deep copying the board.)
        dimension = copy.getDimension();
        numTaken = copy.getNumTaken();
        board = new Tile[dimension][dimension];
        Tile[][] copyBoard = copy.getBoard();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = copyBoard[i][j];
            }
        }

    }

    // Initalize 2D array of size dxd, where the reversi tiles are organized in the middle.
    public void initializeBoard(int d) {
        board = new Tile[d][d];
        numTaken = 0;
        dimension = d;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = Tile.Empty;
            }
        }

        int middle = dimension / 2 - 1;
        board[middle][middle] = Tile.O;
        board[middle + 1][middle + 1] = Tile.O;
        board[middle + 1][middle] = Tile.X;
        board[middle][middle + 1] = Tile.X;
        numTaken += 4;
    }

    // Getters
    public int getNumTaken() {
        return numTaken;
    }

    // Setters
    public void setNumTaken(int numTaken) {
        this.numTaken = numTaken;
    }

    public int getDimension() {
        return dimension;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public Tile getValue(Cell cell) {
        return board[cell.getRow()][cell.getColumn()];
    }

    public void changeCell(Cell cell, Tile value) {
        int row = cell.getRow(), column = cell.getColumn();
        changeCell(row, column, value);
    }

    public void changeCell(int row, int column, Tile value) {
        if (board[row][column] == Tile.Empty && value != Tile.Empty) {
            numTaken++;
        }
        board[row][column] = value;
    }

    // Flip all the items from CellCollection(X turns to O and O turns to X)
    public void flipAll(CellCollection collection) {
        for (Cell current : collection) {
            if (getValue(current) == Tile.X) {
                changeCell(current, Tile.O);
            } else if (getValue(current) == Tile.O) {
                changeCell(current, Tile.X);
            }
        }
    }

    // Count the score(how many tiles) the player has on board.
    public int countTile(Tile player) {
        int count = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == player) {
                    count++;
                }
            }
        }
        return count;
    }
}