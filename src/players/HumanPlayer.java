package players;

import constants.Tile;
import game.Board;
import game.Cell;
import game.CellCollection;
import graphics.GraphicsHandler;

public class HumanPlayer extends GamePlayer {

    private GraphicsHandler graphics;

    public HumanPlayer(Tile t, GraphicsHandler gh) {
        this(t, gh, "");
    }

    public HumanPlayer(Tile t, GraphicsHandler gh, String name) {
        super(t, name);
        graphics = gh;

    }

    @Override
    public Cell decideMove(Board b, CellCollection possibleMoves) {
        return graphics.pickMove(name);
    }

}
