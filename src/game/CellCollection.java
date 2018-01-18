package game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Pretty straight forward- A collection of Cells.
 * We can use this class this change the Data structure we use for storing
 * cells effeciently without having to change the whole.
 *
 * @author ohad
 */
public class CellCollection implements Iterable<Cell> {

    private List<Cell> collection;

    public CellCollection() {
        collection = new LinkedList<Cell>();
    }

    public void add(Cell cell) {
        collection.add(cell);
    }

    public Cell get(int i) {
        if (i >= size()) {
            return new Cell(-1, -1);
        }
        return collection.get(i);
    }

    public int size() {
        return collection.size();
    }

    @Override
    public Iterator<Cell> iterator() {
        return collection.iterator();
    }

}
