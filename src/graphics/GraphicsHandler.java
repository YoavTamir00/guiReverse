package graphics;

import constants.Tile;
import game.Cell;
import game.CellCollection;

public interface GraphicsHandler {
	
    public void printCurrentBoard();
	
    public void illegalMoveAlert(Cell cell);

    public Cell pickMove(String name);

    public void showWinner(String winner);

    public void noAvailableMoves(String name);

    public void draw() ;
    
    public void mainMenu();
    
    public void setP1Playing(boolean isP1);
    
    public void setP1Score(int score);
    
    public void setP2Score(int score);
}
