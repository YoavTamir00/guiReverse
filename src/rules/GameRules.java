package rules;

import constants.GameState;
import constants.Tile;
import game.Board;
import game.Cell;
import game.CellCollection;

public interface GameRules {

    // May the player choose this Cell?
    public boolean validateMove(Board b, Cell cell, Tile t);

    // The changes we need to make to the game after the tile has picked a valid
    // move.
    public CellCollection cellsToFlip(Board b, Cell cell, Tile t);

    // Receive a board and return the winning player/draw,
    // if the game isn't over 'playing' will be returned.
    public GameState gameOver(Board b);

    // Return all the possible moves for a player.
    public CellCollection allPossibleMoves(Board b, Tile t);
}
