package relop;

import global.RID;
import heap.HeapFile;
import heap.HeapScan;

/**
 * Wrapper for heap file scan, the most basic access method. This "iterator"
 * version takes schema into consideration and generates real tuples.
 */
public class FileScan extends Iterator {

	// TODO: Done
	private HeapFile file;
	private HeapScan heapscan; // Wrapper for heap file scan
	private RID lastRecordID;

	/**
	 * Constructs a file scan, given the schema and heap file.
	 */
	public FileScan(Schema schema, HeapFile file) {
		// TODO: Done
		// Schema from Iterator class, for resulting tuples; must be set in all
		// subclass constructors
		this.setSchema(schema);
		this.file = file;

		this.heapscan = file.openScan();
		lastRecordID = new RID();

		// throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Gives a one-line explaination of the iterator, repeats the call on any
	 * child iterators, and increases the indent depth along the way.
	 */
	public void explain(int depth) {
		this.indent(depth++);
		System.out.println("FileScan depth => " + depth);
		// throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Restarts the iterator, i.e. as if it were just constructed.
	 */
	public void restart() {
		// TODO: Done
		if (isOpen()) {
			close();
		}
		this.heapscan = file.openScan();
		// throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Returns true if the iterator is open; false otherwise.
	 */
	public boolean isOpen() {
		// TODO: Done
		if (heapscan != null) {
			return true;
		}
		return false;
		// throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Closes the iterator, releasing any resources (i.e. pinned pages).
	 */
	public void close() {
		// TODO: close
		if (isOpen()) {
			heapscan.close();
		}
		heapscan = null;

		// throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Returns true if there are more tuples, false otherwise.
	 */
	public boolean hasNext() {
		// TODO: Done
		if (this.isOpen()) {
			return heapscan.hasNext();
		}
		return false;

		// throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Gets the next tuple in the iteration.
	 * 
	 * @throws IllegalStateException
	 *             if no more tuples
	 */
	public Tuple getNext() {
		// TODO: get next tuple
		if (this.hasNext()) {
			Tuple t = new Tuple(this.getSchema(), heapscan.getNext(lastRecordID));
			return t;
		} else {
			throw new IllegalStateException("FilsScan - tuples underflow");
		}

		// throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Gets the RID of the last tuple returned.
	 */
	public RID getLastRID() {
		// TODO: Done
		return lastRecordID;

		// throw new UnsupportedOperationException("Not implemented");
	}

} // public class FileScan extends Iterator
