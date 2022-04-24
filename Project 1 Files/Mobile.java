/**
 * Mobile is used for allowing Boardable objects to be run in a thread
 * @author Andrew Buirge
 *
 */
public abstract class Mobile implements Runnable, Boardable {

	/**
	 * board is a Board object to hold the board of Cells
	 */
	protected Board board;
	
	/**
	 * Mobile constructor takes a Board object and sets the board instance variable
	 * @param board of type Board passed in the creation of the Player object
	 */
	 public Mobile(Board board) {
		this.board = board;
	}
	
	/**
	 * move abstract class for classes that extend Mobile
	 */
	protected abstract void move();
	
	@Override
	public abstract boolean isVisible();

	@Override
	public abstract void run();

}
