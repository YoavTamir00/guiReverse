package game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CellCollection implements Iterable<Cell> {

	// In this class we can change the data structure of which the Cells
	// Are held in, without having to change the rest of the code.
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
		// TODO Auto-generated method stub
		return collection.iterator();
	}

}
