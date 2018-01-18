package players;

import constants.Tile;
import game.Board;
import game.Cell;
import game.CellCollection;

public abstract class GamePlayer {
    protected String name;
    private Tile myTile;

    public GamePlayer(Tile g) {
        this(g, "");
    }

    GamePlayer(Tile g, String n) {
        this.myTile = g;
        this.name = n;
    }

    // Passing the possible moves might help, a PC Tile for example, to pick a
    // move.
    public abstract Cell decideMove(Board b, CellCollection possibleMoves);

    // Getters/Setters.
    public Tile getTile() {
        return myTile;
    }

    public void setTile(Tile t) {
        this.myTile = t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
