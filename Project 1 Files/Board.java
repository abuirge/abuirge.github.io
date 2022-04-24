import java.util.ArrayList;
import java.util.HashMap;

/**
 * Board contains the board constructor, move function and nested class Cell.
 * @author Andrew Buirge
 *
 */
public class Board {

	/**
	 * board is the 2d array that will hold each cell
	 */
	private Cell[][] board;
	/**
	 * height is the number of columns in the board
	 */
	private int height;
	/**
	 * width is the number of rows in the board
	 */
	private int width;
	/**
	 * elementPlace is a HashMap that contains Boardable objects as keys and the value associated is the Cell object
	 */
	private HashMap<Boardable, Cell> elementPlace = new HashMap<Boardable, Cell>();
	/**
	 * hugged is boolean to determine if Jarvis has been hugged
	 */
	private boolean hugged = false;

	/**
	 * Board constructor checks if height and width are within bounds, creates a board with the specified height and width then fills the board
	 * with individual Cell objects
	 * @param height used to create the board with number of columns
	 * @param width used to create the board with number of rows
	 * @throws IllegalArgumentException is thrown if the board is created with a size out of bounds
	 */
	public Board(int height, int width) throws IllegalArgumentException {

		if(height > 100 || height < 0 || width > 100 || width < 0) {
			throw new IllegalArgumentException("Height and Width must be between 1-100");
		}

		this.height = height;
		this.width = width;


		board = new Cell[this.height][this.width];


		for(int i = 0; i < this.height; i++) {
			for(int x = 0; x < this.width; x++) {
				this.board[i][x] = new Cell(i,x);
			}
		}

	}

	/**
	 * move checks if the element exists in the board, gets the current location of the element then creates a new cell. Then move enters a switch
	 * statement. Within each case the proper calculations are done to get the new location then removes from current location, places in new location
	 * and prints the board
	 * @param dir is the enumeration representing each direction
	 * @param elem the element to move
	 * @return true if move was legal, false if not
	 * @throws IllegalArgumentException checks for the element to exist in the board
	 */
	public synchronized boolean move(Direction dir, Boardable elem) throws IllegalArgumentException{

		if(!elementPlace.containsKey(elem)) {
			throw new IllegalArgumentException("Element is not in Board");
		}

		Cell curLocation = this.elementPlace.get(elem);
		Cell newLocation = new Cell(0, 0);

		switch(dir) {
		case UP_LEFT:
			newLocation.col = curLocation.col - 1;
			newLocation.row = curLocation.row - 1;


			if(newLocation.col > width - 1 || newLocation.col < 0 || newLocation.row > height - 1 || newLocation.row < 0) {
				newLocation.col = curLocation.col;
				newLocation.row = curLocation.row;
			}

			curLocation.removeElement(elem);
			placeElement(elem, newLocation.row, newLocation.col);

			break;

		case UP:
			newLocation.col = curLocation.col;
			newLocation.row = curLocation.row - 1;

			if(newLocation.col > width - 1 || newLocation.col < 0 || newLocation.row > height - 1 || newLocation.row < 0) {
				newLocation.col = curLocation.col;
				newLocation.row = curLocation.row;
			}

			curLocation.removeElement(elem);
			placeElement(elem, newLocation.row, newLocation.col);

			break;

		case UP_RIGHT:
			newLocation.col = curLocation.col + 1;
			newLocation.row = curLocation.row - 1;

			if(newLocation.col > width - 1 || newLocation.col < 0 || newLocation.row > height - 1 || newLocation.row < 0) {
				newLocation.col = curLocation.col;
				newLocation.row = curLocation.row;
			}

			curLocation.removeElement(elem);
			placeElement(elem, newLocation.row, newLocation.col);

			break;

		case LEFT:
			newLocation.col = curLocation.col - 1;
			newLocation.row = curLocation.row;

			if(newLocation.col > width - 1 || newLocation.col < 0 || newLocation.row > height - 1 || newLocation.row < 0) {
				newLocation.col = curLocation.col;
				newLocation.row = curLocation.row;
			}

			curLocation.removeElement(elem);
			placeElement(elem, newLocation.row, newLocation.col);

			break;

		case RIGHT:
			newLocation.col = curLocation.col + 1;
			newLocation.row = curLocation.row;

			if(newLocation.col > width - 1 || newLocation.col < 0 || newLocation.row > height - 1 || newLocation.row < 0) {
				newLocation.col = curLocation.col;
				newLocation.row = curLocation.row;
			}

			curLocation.removeElement(elem);
			placeElement(elem, newLocation.row, newLocation.col);

			break;

		case DOWN_LEFT:
			newLocation.col = curLocation.col - 1;
			newLocation.row = curLocation.row + 1;

			if(newLocation.col > width - 1 || newLocation.col < 0 || newLocation.row > height - 1 || newLocation.row < 0) {
				newLocation.col = curLocation.col;
				newLocation.row = curLocation.row;
			}

			curLocation.removeElement(elem);
			placeElement(elem, newLocation.row, newLocation.col);

			break;

		case DOWN:
			newLocation.col = curLocation.col;
			newLocation.row = curLocation.row + 1;

			if(newLocation.col > width - 1 || newLocation.col < 0 || newLocation.row > height - 1 || newLocation.row < 0) {
				newLocation.col = curLocation.col;
				newLocation.row = curLocation.row;
			}

			curLocation.removeElement(elem);
			placeElement(elem, newLocation.row, newLocation.col);

			break;

		case DOWN_RIGHT:
			newLocation.col = curLocation.col + 1;
			newLocation.row = curLocation.row + 1;

			if(newLocation.col > width - 1 || newLocation.col < 0 || newLocation.row > height - 1 || newLocation.row < 0) {
				newLocation.col = curLocation.col;
				newLocation.row = curLocation.row;
			}

			curLocation.removeElement(elem);
			placeElement(elem, newLocation.row, newLocation.col);

			break;

		default:
			return false;
		}

		return true;
	}

	/**
	 * placeElement checks if the row and column are within bounds, takes an element and places it in the board in the desired cell
	 * based on row and column.
	 * @param elem the element to be placed
	 * @param row to place the element
	 * @param col column to place the element
	 * @return true if element was placed, false if row and column were out of bounds
	 */
	public synchronized boolean placeElement(Boardable elem, int row, int col) {
		if(row > this.height || col > this.width || row < 0 || col < 0) {
			return false;
		}

		Cell cur = board[row][col];
		cur.addElement(elem);
		elementPlace.put(elem, cur);

		return true;
	}

	/**
	 * removedElement takes a Boardable object, checks if it exists in the HashMap, removes from the cell and HashMap if it exists
	 * @param elem
	 * @return
	 */
	public synchronized boolean removeElement(Boardable elem) {

		if(!elementPlace.containsKey(elem)) {
			return false;
		}else {
			Cell cur = elementPlace.get(elem);
			cur.removeElement(elem);
			elementPlace.remove(elem);
			return true;
		}
	}

	/**
	 * getRow checks if the Boarable object exists, if true gets the current Cell and row of the Cell
	 * @param elem Boardable object to get the row of
	 * @return integer value of the row
	 * @throws IllegalArgumentException
	 */
	public synchronized int getRow(Boardable elem) throws IllegalArgumentException {
		if(!elementPlace.containsKey(elem)) {
			throw new IllegalArgumentException("Element does not exist in board");
		}else {
			Cell cur = elementPlace.get(elem);
			return cur.row;
		}
	}

	/**
	 * checks if the Boarable object exists, if true gets the current Cell and column of the Cell
	 * @param elem Boardable object to get the column of
	 * @return integer value of the column
	 * @throws IllegalArgumentException
	 */
	public synchronized int getColumn(Boardable elem) throws IllegalArgumentException {
		if(!elementPlace.containsKey(elem)) {
			throw new IllegalArgumentException("Element does not exist in board");
		}else {
			Cell cur = elementPlace.get(elem);
			return cur.col;
		}
	}


	/**
	 * setHugged method takes a boolean and sets the instance of hugged to the new boolean value
	 * @param hugged boolean value to set the instance variable to
	 */
	public synchronized void setHugged(boolean hugged) {
		this.hugged = hugged;
	}

	/**
	 * beenHugged checks for the instance of hugged boolean value
	 * @return boolean instance value
	 */
	public synchronized boolean beenHugged() {
		return this.hugged;
	}


	/**
	 * printBoard prints the board by printing each Cell in the current row in a nested loop. The outer loop gets the inner loop from column 
	 * to column to print the correct height
	 */
	public synchronized void printBoard() {
		for(int i = 0; i < this.height; i++) {
			if(i > 0) {
				System.out.println();
			}

			for(int x = 0; x < this.width; x++) {
				Cell current = board[i][x]; 
				System.out.print(current.toString());

			}
		}
		System.out.println("\n");
	}


	/**
	 * Cell is a nested class within Board. Cell is used to hold elements and printing of the board
	 * @author Andrew Buirge
	 *
	 */
	private class Cell{
		/**
		 * row holds integer value of the row that the Cell is in
		 */
		private int row;
		/**
		 * col holds the integer value of the column that the Cell is in
		 */
		private int col;
		/**
		 * isVisible holds the state of the Cell for printing
		 */
		private boolean isVisible = false;
		/**
		 * elements is an ArrayList of Boardable objects within the Cell
		 */
		private ArrayList<Boardable> elements = new ArrayList<Boardable>();

		/**
		 * Cell constructor sets the row and column of the Cell
		 * @param row the Cell is in of the board
		 * @param col the Cell is in of the board
		 */
		public Cell(int row, int col) {
			this.row = row;
			this.col = col;
		}

		/**
		 * addElement takes a Boardable object, places it into the elements ArrayList of the Cell and sets the Cell visibility to the 
		 * Boardable objects visibility
		 * @param elem is the Boardable object to be placed
		 */
		public synchronized void addElement(Boardable elem) {


			if(!this.isVisible) {
				this.isVisible = elem.isVisible();
			}

			if(this.elements.isEmpty()) {
				this.elements.add(elem);
				elementPlace.put(elem, this);
			}else {
				Boardable curElem = this.elements.get(0);
				if(curElem.share(elem)) {
					this.elements.add(elem);
					elementPlace.put(elem, this);
				}
			}
		}

		/**
		 * removeElement checks if the Boardable object exists in the cell, if it does it removes the element from both the elements ArrayList of
		 * the Cell and from the elementPlace HashMap
		 * @param elem Boardable object to be removed
		 * @return true if the element was removed, false if the element does not exist in the Cell
		 */
		public synchronized boolean removeElement(Boardable elem) {
			if(!elements.contains(elem)) {
				return false;
			}else {
				elements.remove(elem);
				elementPlace.remove(elem);
				return true;
			}
		}

		/**
		 * toString is used in the printing of the board. As the board is printed, each Cell's toString is called. If the Cell is not visible
		 * a pound is returned, if the Cell is visible and empty a space is returned and if the Cell is visible and contains an element, the 
		 * last element to be added to the cell toString is called and returned.
		 */
		public synchronized String toString() { 
			if(!this.isVisible) {
				return "#";
			}else if(elements.isEmpty()) {
				return " ";
			}else {
				return elements.get(elements.size()-1).toString().replace("[", "").replace("]", "");
			}
		}
	}
}


