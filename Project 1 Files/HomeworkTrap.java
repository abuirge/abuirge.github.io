/**
 * HomeworkTrap implements Boardable as it is to be placed on a Board. It is placed by a Jarvis and is used to delay the Player
 * @author Andrew Buirge
 *
 */
public class HomeworkTrap implements Boardable {
	/**
	 * board is a Board object to hold the board of Cells
	 */
	private Board board;
	
	/**
	 * HomeworkTrap constructor takes a Board object and uses the board for placing the HomeworkTrap object
	 * @param board of type Board passed in the creation of the HomeworkTrap object
	 */
	public HomeworkTrap(Board board) {
		this.board = board;
	}
	
	/**
	 * share determines if the HomeworkTrap can share with the Boardable object. True if element is instance of Jarvis or Player object.
	 * False if element is instance of HomeworkTrap
	 * @param Boardable element 
	 */
	public boolean share(Boardable elem) {
		
		if(elem instanceof Jarvis) {
			return true;
		}else if(elem instanceof Player) {
			((Player)elem).setDelay(5000);
			System.out.println("It's a Homework Trap! Delayed for 5 seconds!");
			board.removeElement(this);
			return true;
		}else if(elem instanceof HomeworkTrap) {
			return false;
		}
		return false;
	}
	
	@Override
	public boolean isVisible() {
		return false;
	}
	
	/**
	 * toString returns a space to be printed on the board
	 */
	public String toString() {
		return " ";
	}

}
