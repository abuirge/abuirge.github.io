import java.util.Random;

/**
 * Jarvis is a Mobile object that is the goal of the game. It is able to be run in a Thread
 * @author Andrew Buirge
 *
 */
public class Jarvis extends Mobile{

	/**
	 * trapCounter is an integer value used to determine if the Jarvis object is allowed to place a HomeworkTrap
	 */
	private int trapCounter;
	/**
	 * board is a Board object to hold the board of Cells
	 */
	private Board board;

	/**
	 * Jarvis  constructor takes a Board object and uses the board for moving the Jarvis object
	 * @param board of type Board passed in the creation of the Jarvis object
	 */
	public Jarvis(Board board) {
		super(board);
		this.board = board;

	}

	/**
	 * layTrap places a new HomeworkTrap object on the board
	 */
	private void layTrap() {
		board.placeElement(new HomeworkTrap(board), board.getRow(this), board.getColumn(this));
	}

	/**
	 * run calls the move method until Jarvis has been hugged
	 */
	public synchronized void run() {
		do {
			move();
		}while(board.beenHugged() == false);
	}

	/**
	 * share determines if Boardable object can be in the same cell as this. If element is Player, hugged is set to true. If element is
	 * HomeworkTrap, the cell can be shared.
	 */
	public boolean share(Boardable elem) {
		if(elem instanceof Player) {
			board.setHugged(true);
			return false;
		}else if(elem instanceof HomeworkTrap)
			return true;
		return true;
	}


	@Override
	protected void move() {
		Random rand = new Random();
		int direction = rand.nextInt(8)+1;


		if(direction == 1) {
			board.move(Direction.UP_LEFT, this);
		}else if(direction == 2) {
			board.move(Direction.UP, this);
		}else if(direction == 3) {
			board.move(Direction.UP_RIGHT, this);
		}else if(direction == 4) {
			board.move(Direction.LEFT, this);
		}else if(direction == 5) {
			board.move(Direction.RIGHT, this);
		}else if(direction == 6) {
			board.move(Direction.DOWN_LEFT, this);
		}else if(direction == 7) {
			board.move(Direction.DOWN, this);
		}else if(direction == 8) {
			board.move(Direction.DOWN_RIGHT, this);
		}

		this.trapCounter = this.trapCounter + 1;

		if(this.trapCounter%6 == 0) {
			this.layTrap();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}



	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/**
	 * toString returns ? to indicate Jarvis on the board
	 */
	public String toString() {
		return "?";
	}


}
